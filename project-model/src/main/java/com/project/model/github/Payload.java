package com.project.model.github;

import java.util.ArrayList;
import java.util.List;

public class Payload {
    private Long pushId;
    private String ref;
    private String head;
    private String before;
    private List<Commit> commits = new ArrayList<>();

    public Long getPushId() {
        return pushId;
    }

    public void setPushId(final Long pushId) {
        this.pushId = pushId;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(final String ref) {
        this.ref = ref;
    }

    public String getHead() {
        return head;
    }

    public void setHead(final String head) {
        this.head = head;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(final String before) {
        this.before = before;
    }

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(final List<Commit> commits) {
        this.commits = commits;
    }

    @Override
    public String toString() {
        return "Payload [pushId=" + pushId + ", ref=" + ref + ", head=" + head
                + ", before=" + before + ", commits=" + commits + "]";
    }
}