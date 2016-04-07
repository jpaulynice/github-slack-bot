package com.project.rest.client;

import com.project.model.slack.SlackResponse;

public interface SlackClient {
    SlackResponse publish(final String event);
}