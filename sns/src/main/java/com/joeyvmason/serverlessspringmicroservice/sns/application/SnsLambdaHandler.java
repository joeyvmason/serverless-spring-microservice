package com.joeyvmason.serverlessspringmicroservice.sns.application;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

@SuppressWarnings("unused")
public class SnsLambdaHandler implements RequestHandler<SNSEvent, Void> {

    // Just used by Amazon

    private SnsLambdaContainer snsLambdaContainer;

    @Override
    public Void handleRequest(SNSEvent input, Context context) {
        if (snsLambdaContainer == null) {
            snsLambdaContainer = new SnsLambdaContainer(SnsConfig.class);
        }

        snsLambdaContainer.handleRequest(input, context);
        return null;
    }


}
