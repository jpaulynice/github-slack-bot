package com.project.model.slack;

public class SlackResponse {
    private boolean ok;
    private String error;
    private String channel;
    private SlackMessage message;

    public SlackResponse() {
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(final boolean ok) {
        this.ok = ok;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(final String channel) {
        this.channel = channel;
    }

    public SlackMessage getMessage() {
        return message;
    }

    public void setMessage(final SlackMessage message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SlackResponse [ok=" + ok + ", error=" + error + ", channel="
                + channel + ", message=" + message + "]";
    }
}
