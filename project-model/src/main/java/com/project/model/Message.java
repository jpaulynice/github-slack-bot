package com.project.model;

/**
 * Object to show human readable message translation from the API.
 *
 * @author Jay Paulynice (jay.paulynice@gmail.com)
 */
public class Message {
    private int status;
    private String info;

    public Message() {
    }

    public Message(final int status, final String info) {
        this.status = status;
        this.info = info;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(final int status) {
        this.status = status;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(final String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Message [status=" + status + ", info=" + info + "]";
    }
}
