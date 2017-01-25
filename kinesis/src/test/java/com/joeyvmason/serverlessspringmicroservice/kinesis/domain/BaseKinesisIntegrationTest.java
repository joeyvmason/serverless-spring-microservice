package com.joeyvmason.serverlessspringmicroservice.kinesis.domain;

import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.joeyvmason.serverlessspringmicroservice.core.application.BaseCoreIntegrationTest;
import com.joeyvmason.serverlessspringmicroservice.kinesis.application.KinesisConfig;
import com.joeyvmason.serverlessspringmicroservice.kinesis.application.KinesisLambdaContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {KinesisConfig.class, KinesisIntegrationTestConfig.class})
public class BaseKinesisIntegrationTest extends BaseCoreIntegrationTest {

    @Autowired
    private KinesisLambdaContainer kinesisLambdaContainer;

    @Autowired
    private MockLambdaContext mockLambdaContext;

    protected void handleRequest(KinesisEvent input) {
        kinesisLambdaContainer.handleRequest(input, mockLambdaContext);
    }


}