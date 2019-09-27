package com.ilustris.motiv.beans;

public class Pics {

    public Pics() {
    }

    public Pics(String uri, String id, String name) {
        this.uri = uri;
        this.id = id;
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    private String uri;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
