package com.joeyvmason.serverlessspringmicroservice.kinesis.domain;

import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.joeyvmason.serverlessspringmicroservice.core.application.CoreIntegrationTestConfig;
import com.joeyvmason.serverlessspringmicroservice.kinesis.application.KinesisConfig;
import com.joeyvmason.serverlessspringmicroservice.kinesis.application.KinesisLambdaContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({KinesisConfig.class, CoreIntegrationTestConfig.class})
public class KinesisIntegrationTestConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public MockLambdaContext lambdaContext() {
        return new MockLambdaContext();
    }

    @Bean
    public KinesisLambdaContainer kinesisLambdaContainer() {
        return new KinesisLambdaContainer(applicationContext);
    }
}
