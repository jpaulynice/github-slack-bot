package com.project.model.github;

public class Commit {
    private String sha;
    private String message;
    private String url;
    private Author author;

    public Commit() {
    }

    public String getSha() {
        return sha;
    }

    public void setSha(final String sha) {
        this.sha = sha;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(final Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Commit [sha=" + sha + ", message=" + message + ", url=" + url
                + ", author=" + author + "]";
    }
}
