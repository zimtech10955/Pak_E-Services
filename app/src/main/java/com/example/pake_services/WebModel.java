package com.example.pake_services;

public class WebModel {
    String title, link;

    public WebModel(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public WebModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
