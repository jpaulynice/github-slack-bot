package com.project.rest.client;

import java.util.Map;

import okhttp3.Response;

public interface Client {
    Response get(final String url, final Map<String, String> headers);

    Response post(String url, String json);
}