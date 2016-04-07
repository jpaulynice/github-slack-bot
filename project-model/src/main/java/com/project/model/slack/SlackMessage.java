package com.project.model.slack;

public class SlackMessage {
    private String text;
    private String username;
    private String type;
    private String subtype;

    public SlackMessage() {
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * @return the subtype
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * @param subtype the subtype to set
     */
    public void setSubtype(final String subtype) {
        this.subtype = subtype;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SlackMessage [text=" + text + ", username=" + username
                + ", type=" + type + ", subtype=" + subtype + "]";
    }
}