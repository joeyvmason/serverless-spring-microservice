package com.joeyvmason.serverlessspringmicroservice.sns.domain;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joeyvmason.serverlessspringmicroservice.core.domain.articles.Article;
import com.joeyvmason.serverlessspringmicroservice.core.domain.articles.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SnsEventProcessor implements RequestHandler<SNSEvent, Void> {
    private static final Logger LOG = LoggerFactory.getLogger(SnsEventProcessor.class);

    private final ObjectMapper objectMapper;
    private final ArticleRepository articleRepository;

    @Autowired
    public SnsEventProcessor(ObjectMapper objectMapper, ArticleRepository articleRepository) {
        this.objectMapper = objectMapper;
        this.articleRepository = articleRepository;
    }

    @Override
    public Void handleRequest(SNSEvent input, Context context) {
        input.getRecords().forEach(record -> {
            try {
                SNSEvent.SNSRecord snsRecord = input.getRecords().get(0);
                Article article = objectMapper.readValue(snsRecord.getSNS().getMessage(), Article.class);
                LOG.info("Received request to update Article({})", article);

                Article articleFromDB = articleRepository.findOne(article.getId());

                LOG.info("Updating Article({})", articleFromDB);
                articleFromDB.setTitle(article.getTitle());
                articleFromDB.setBody(article.getBody());
                articleRepository.save(article);
            } catch (RuntimeException e) {
                LOG.warn("Unable to process event", e);
                throw e;
            } catch (Exception e) {
                LOG.warn("Unable to process event", e);
                throw new RuntimeException(e);
            }
        });

        return null;
    }
}
