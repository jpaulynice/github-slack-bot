package com.project.rest.client.impl;

import java.io.IOException;
import java.util.Collections;

import okhttp3.OkHttpClient;
import okhttp3.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.project.model.slack.SlackResponse;
import com.project.rest.client.BaseClient;
import com.project.rest.client.SlackClient;

@Component
public class DefaultSlackClient extends BaseClient implements SlackClient {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${slack.message.api}")
    private String slackAPI;

    public DefaultSlackClient() {
        init();
    }

    private void init() {
        // here we could add custom code for authentication
        client = new OkHttpClient.Builder().build();
        log.debug("initialized slack api client.");
    }

    @Override
    public SlackResponse publish(final String event) {
        log.debug("publishing to slack event={}", event);

        final StringBuilder builder = new StringBuilder(slackAPI);
        builder.append(event);

        final Response res = get(builder.toString(), Collections.emptyMap());

        String eventsJson = null;
        try {
            eventsJson = res.body().string();
        } catch (final IOException e) {
            log.error("exception getting events", e);
        }

        return GSON.fromJson(eventsJson, SlackResponse.class);
    }
}