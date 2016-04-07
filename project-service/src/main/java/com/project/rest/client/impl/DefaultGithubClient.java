package com.project.rest.client.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.project.model.github.GithubEvent;
import com.project.rest.client.BaseClient;
import com.project.rest.client.GithubClient;

@Component
public class DefaultGithubClient extends BaseClient implements GithubClient {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final Map<String, String> repoEtags = new HashMap<>();
    private static final String ETAG = "Etag";
    private static final String IF_NONE_MATCH = "If-None-Match";

    @Value("${github.login}")
    private String login;

    @Value("${github.pw}")
    private String pw;

    @Value("${github.repo.events}")
    private String eventsAPI;

    public DefaultGithubClient() {
        init();
    }

    private void init() {
        client = new OkHttpClient.Builder().authenticator(
                (final Route route, final Response response) -> {
                    return response.request().newBuilder().header(
                            "Authorization", Credentials.basic(login, pw))
                            .build();
                }).build();
        log.debug("initialized github client.");
    }

    @Override
    public List<GithubEvent> getEvents() {
        final List<GithubEvent> all = new ArrayList<>();
        final String[] urls = eventsAPI.split(",");
        for (final String url : urls) {
            all.addAll(fetch(url));
        }

        return all;
    }

    private List<GithubEvent> fetch(final String url) {
        final Map<String, String> headers = new HashMap<>();
        final String lastEtag = repoEtags.get(url);
        if (lastEtag != null) {
            headers.put(IF_NONE_MATCH, lastEtag);
        }
        String eventsJson = null;

        final Response res = get(url, headers);
        if (res != null) {
            String etag = res.headers().get(ETAG);
            etag = etag.replace("W/", "");
            repoEtags.put(url, etag);

            try {
                eventsJson = res.body().string();
            } catch (final IOException e) {
                log.error("exception getting events", e);
            }
        }
        if (eventsJson == null || eventsJson.isEmpty()) {
            return Collections.emptyList();
        }

        return GSON.fromJson(eventsJson, new TypeToken<List<GithubEvent>>() {
        }.getType());
    }
}