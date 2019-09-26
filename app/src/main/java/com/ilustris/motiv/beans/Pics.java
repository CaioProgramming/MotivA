package com.ilustris.motiv.beans;

public class Pics {
    public Pics(String uri) {
        this.uri = uri;
    }

    public Pics() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    private String uri;
}
