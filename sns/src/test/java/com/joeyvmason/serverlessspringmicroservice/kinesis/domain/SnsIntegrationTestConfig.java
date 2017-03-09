package com.joeyvmason.serverlessspringmicroservice.kinesis.domain;

import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.joeyvmason.serverlessspringmicroservice.core.application.CoreIntegrationTestConfig;
import com.joeyvmason.serverlessspringmicroservice.sns.application.SnsConfig;
import com.joeyvmason.serverlessspringmicroservice.sns.application.SnsLambdaContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SnsConfig.class, CoreIntegrationTestConfig.class})
public class SnsIntegrationTestConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public MockLambdaContext lambdaContext() {
        return new MockLambdaContext();
    }

    @Bean
    public SnsLambdaContainer kinesisLambdaContainer() {
        return new SnsLambdaContainer(applicationContext);
    }
}
