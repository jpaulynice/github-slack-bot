package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.github.GithubEvent;
import com.project.rest.client.GithubClient;
import com.project.service.GithubService;

@Service
public class GithubServiceImpl implements GithubService {
    private final GithubClient client;

    @Autowired
    public GithubServiceImpl(final GithubClient client) {
        this.client = client;
    }

    @Override
    public List<GithubEvent> getPublicEvents() {
        return client.getEvents();
    }
}