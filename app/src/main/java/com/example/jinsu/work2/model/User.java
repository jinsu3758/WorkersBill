package com.example.jinsu.work2.model;

public class User {

    private String login;
    private String html_url;

    public User(String login, String html_url) {
        this.login = login;
        this.html_url = html_url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getLogin() {
        return login;
    }

    public String getHtml_url() {
        return html_url;
    }
}
