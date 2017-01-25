package com.joeyvmason.serverlessspringmicroservice.core.application;

import com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;
import com.joeyvmason.serverlessspringmicroservice.web.application.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = {WebConfig.class, WebIntegrationTestConfig.class})
//@ContextConfiguration(classes = {WebIntegrationTestConfig.class}) // This is better, but causes IntelliJ warnings because of a bug with IntelliJ
@WebAppConfiguration
@TestExecutionListeners(inheritListeners = false, listeners = {DependencyInjectionTestExecutionListener.class})
public class BaseWebIntegrationTest extends BaseCoreIntegrationTest {

    @Autowired
    protected MockLambdaContext lambdaContext;

    @Autowired
    protected SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

}