package com.joeyvmason.serverlessspringmicroservice.core.application;

import com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;
import com.joeyvmason.serverlessspringmicroservice.web.application.MvcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = {MvcConfig.class, WebIntegrationTestConfig.class})
@WebAppConfiguration
@TestExecutionListeners(inheritListeners = false, listeners = {DependencyInjectionTestExecutionListener.class})
public class BaseWebIntegrationTest extends BaseCoreIntegrationTest {

    @Autowired
    protected MockLambdaContext lambdaContext;

    @Autowired
    protected SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

}