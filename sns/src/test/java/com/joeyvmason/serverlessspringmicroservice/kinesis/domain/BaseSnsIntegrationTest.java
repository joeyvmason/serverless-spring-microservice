package com.joeyvmason.serverlessspringmicroservice.kinesis.domain;

import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.joeyvmason.serverlessspringmicroservice.core.application.BaseCoreIntegrationTest;
import com.joeyvmason.serverlessspringmicroservice.sns.application.SnsConfig;
import com.joeyvmason.serverlessspringmicroservice.sns.application.SnsLambdaContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {SnsConfig.class, SnsIntegrationTestConfig.class})
public class BaseSnsIntegrationTest extends BaseCoreIntegrationTest {

    @Autowired
    private SnsLambdaContainer snsLambdaContainer;

    @Autowired
    private MockLambdaContext mockLambdaContext;

    protected void handleRequest(SNSEvent input) {
        snsLambdaContainer.handleRequest(input, mockLambdaContext);
    }


}