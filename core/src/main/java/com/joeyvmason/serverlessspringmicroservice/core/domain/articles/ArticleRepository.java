package com.joeyvmason.serverlessspringmicroservice.core.domain.articles;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ArticleRepository {

    private Map<String, Article> articles = new HashMap<>();

    public Article save(Article article) {
        if (article.getId() == null) {
            article.setId(UUID.randomUUID().toString());
        }

        articles.put(article.getId(), article);
        return article;
    }

    public Collection<Article> findAll() {
        return articles.values();
    }

    public Article findOne(String id) {
        return articles.get(id);
    }

    public void deleteAll() {
        articles.clear();
    }

}
