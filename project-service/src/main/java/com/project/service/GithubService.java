package com.project.service;

import java.util.List;

import com.project.model.github.GithubEvent;

public interface GithubService {
    List<GithubEvent> getPublicEvents();
}