package com.joeyvmason.serverlessspringmicroservice.core.application;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joeyvmason.serverlessspringmicroservice.web.application.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@Configuration
@Import({WebConfig.class, CoreIntegrationTestConfig.class})
public class WebIntegrationTestConfig {

    @Autowired
    private ConfigurableWebApplicationContext applicationContext;

    @Bean
    public MockLambdaContext lambdaContext() {
        return new MockLambdaContext();
    }

    @Bean
    public SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> springLambdaContainerHandler() throws ContainerInitializationException {
        SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler = SpringLambdaContainerHandler.getAwsProxyHandler(applicationContext);
        handler.setRefreshContext(false);
        return handler;
    }
}
