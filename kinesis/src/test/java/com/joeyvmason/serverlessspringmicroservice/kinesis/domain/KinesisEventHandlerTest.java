package com.joeyvmason.serverlessspringmicroservice.kinesis.domain;

import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.joeyvmason.serverlessspringmicroservice.core.domain.articles.Article;
import com.joeyvmason.serverlessspringmicroservice.core.domain.articles.ArticleRepository;
import com.joeyvmason.serverlessspringmicroservice.core.domain.articles.ArticleTestBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.nio.ByteBuffer;

import static org.fest.assertions.api.Assertions.assertThat;

public class KinesisEventHandlerTest extends BaseKinesisIntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private KinesisLambdaHandler kinesisLambdaHandler;

    @Autowired
    private MockLambdaContext mockLambdaContext;

    @Test
    public void shouldHandleEvent() throws Exception {
        //given
        Article article = articleRepository.save(ArticleTestBuilder.valid().build());
        article.setTitle(RandomStringUtils.randomAlphanumeric(10));
        article.setBody(RandomStringUtils.randomAlphanumeric(10));

        KinesisEvent kinesisEvent = new KinesisEvent();
        KinesisEvent.KinesisEventRecord record = new KinesisEvent.KinesisEventRecord();

        KinesisEvent.Record kinesis = new KinesisEvent.Record();
        kinesis.setData(ByteBuffer.wrap(objectMapper.writeValueAsBytes(article)));
        record.setKinesis(kinesis);

        kinesisEvent.setRecords(Lists.newArrayList(record));

        //when
        kinesisLambdaHandler.handleRequest(kinesisEvent, mockLambdaContext);

        //then
        Article articleFromDB = articleRepository.findOne(article.getId());
        assertThat(articleFromDB.getTitle()).isEqualTo(article.getTitle());
        assertThat(articleFromDB.getBody()).isEqualTo(article.getBody());
    }

}
