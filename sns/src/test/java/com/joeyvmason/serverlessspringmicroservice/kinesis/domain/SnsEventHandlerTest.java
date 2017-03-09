package com.joeyvmason.serverlessspringmicroservice.kinesis.domain;

import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joeyvmason.serverlessspringmicroservice.core.domain.articles.Article;
import com.joeyvmason.serverlessspringmicroservice.core.domain.articles.ArticleRepository;
import com.joeyvmason.serverlessspringmicroservice.core.domain.articles.ArticleTestBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import org.testng.collections.Lists;


import static org.fest.assertions.api.Assertions.assertThat;

public class SnsEventHandlerTest extends BaseSnsIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void shouldHandleEvent() throws Exception {
        //given
        Article article = articleRepository.save(ArticleTestBuilder.valid().build());
        article.setTitle(RandomStringUtils.randomAlphanumeric(10));
        article.setBody(RandomStringUtils.randomAlphanumeric(10));

        SNSEvent snsEvent = new SNSEvent();

        SNSEvent.SNSRecord snsRecord = new SNSEvent.SNSRecord();
        SNSEvent.SNS sns = new SNSEvent.SNS();
        sns.setMessage(objectMapper.writeValueAsString(article));
        snsRecord.setSns(sns);
        snsEvent.setRecords(Lists.newArrayList(snsRecord));

        //when
        handleRequest(snsEvent);

        //then
        Article articleFromDB = articleRepository.findOne(article.getId());
        assertThat(articleFromDB.getTitle()).isEqualTo(article.getTitle());
        assertThat(articleFromDB.getBody()).isEqualTo(article.getBody());
    }

}
