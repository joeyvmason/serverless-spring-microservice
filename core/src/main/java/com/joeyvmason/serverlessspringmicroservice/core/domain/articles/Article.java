package com.joeyvmason.serverlessspringmicroservice.core.domain.articles;

import com.joeyvmason.serverlessspringmicroservice.core.domain.Identifiable;

public class Article extends Identifiable {
    private String title;
    private String body;

    public Article() {}

    public Article(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
