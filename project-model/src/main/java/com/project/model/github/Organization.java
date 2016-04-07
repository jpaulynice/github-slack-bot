package com.project.model.github;

public class Organization {
    private Long id;
    private String login;
    private String url;

    public Organization() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Organization [id=" + id + ", login=" + login + ", url=" + url
                + "]";
    }
}