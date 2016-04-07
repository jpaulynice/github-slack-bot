package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exception.SlackApiException;
import com.project.model.slack.SlackResponse;
import com.project.rest.client.SlackClient;
import com.project.service.SlackService;

@Service
public class SlackServiceImpl implements SlackService {
    private final SlackClient client;

    @Autowired
    public SlackServiceImpl(final SlackClient client) {
        this.client = client;
    }

    @Override
    public SlackResponse publish(final String event) {
        final SlackResponse res = client.publish(event);
        checkResponse(res);
        return res;
    }

    private void checkResponse(final SlackResponse res) {
        if (!res.isOk()) {
            String message = res.getError();
            if ("not_authed".equals(message)) {
                message = "A valid token is required to authenticate with the slack api.  "
                        + "Please stop the scheduler and "
                        + "update config.properties with correct token";
            }

            throw new SlackApiException(message);
        }
    }
}