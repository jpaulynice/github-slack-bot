package com.project.rest.client;

import java.util.List;

import com.project.model.github.GithubEvent;

public interface GithubClient {
    List<GithubEvent> getEvents();
}