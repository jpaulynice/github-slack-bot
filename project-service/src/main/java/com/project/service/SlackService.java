package com.project.service;

import com.project.model.slack.SlackResponse;

public interface SlackService {
    SlackResponse publish(final String event);
}
