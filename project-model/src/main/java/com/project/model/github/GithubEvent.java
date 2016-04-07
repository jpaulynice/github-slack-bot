package com.project.model.github;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class GithubEvent {
    private Long id;
    private String type;
    private Author actor;
    private Organization org;
    private Payload payload;
    private Repository repo;

    @SerializedName("public")
    private boolean isPublic;

    @SerializedName("created_at")
    private Date created;

    public GithubEvent() {
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(final boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Author getActor() {
        return actor;
    }

    public void setActor(final Author actor) {
        this.actor = actor;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(final Organization org) {
        this.org = org;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(final Payload payload) {
        this.payload = payload;
    }

    public Repository getRepo() {
        return repo;
    }

    public void setRepo(final Repository repo) {
        this.repo = repo;
    }

    @Override
    public String toString() {
        return "GithubEvent [id=" + id + ", type=" + type + ", actor=" + actor
                + ", org=" + org + ", payload=" + payload + ", repo=" + repo
                + ", isPublic=" + isPublic + ", created=" + created + "]";
    }
}