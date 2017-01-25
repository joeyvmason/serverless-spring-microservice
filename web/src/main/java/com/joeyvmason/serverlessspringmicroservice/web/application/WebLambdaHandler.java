package com.joeyvmason.serverlessspringmicroservice.web.application;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.internal.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebLambdaHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {
    private static final Logger LOG = LoggerFactory.getLogger(WebLambdaHandler.class);

    private SpringLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    private boolean initialized = false;

    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
        if (!initialized) {

            try {
                handler = SpringLambdaContainerHandler.getAwsProxyHandler(WebConfig.class);
                initialized = true;
            } catch (ContainerInitializationException e) {
                LOG.warn("Unable to create handler", e);
                return null;
            }
        }

        return handler.proxy(awsProxyRequest, context);
    }

}
