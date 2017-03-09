package com.joeyvmason.serverlessspringmicroservice.kinesis.domain;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joeyvmason.serverlessspringmicroservice.core.domain.articles.Article;
import com.joeyvmason.serverlessspringmicroservice.core.domain.articles.ArticleRepository;
import com.joeyvmason.serverlessspringmicroservice.kinesis.application.AbstractLambdaContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KinesisLambdaContainer implements AbstractLambdaContainer<KinesisEvent, Void> {
    private static final Logger LOG = LoggerFactory.getLogger(KinesisLambdaContainer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Void handleRequest(KinesisEvent input, Context context) throws Exception {
        input.getRecords().forEach(record -> {
            try {
                Article article = objectMapper.readValue(record.getKinesis().getData().array(), Article.class);
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
