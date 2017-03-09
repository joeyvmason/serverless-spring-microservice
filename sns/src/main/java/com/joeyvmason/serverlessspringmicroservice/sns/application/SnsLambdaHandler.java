package com.joeyvmason.serverlessspringmicroservice.sns.application;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;

@SuppressWarnings("unused")
public class SnsLambdaHandler implements RequestHandler<KinesisEvent, Void> {

    // Just used by Amazon

    private SnsLambdaContainer snsLambdaContainer;

    @Override
    public Void handleRequest(KinesisEvent input, Context context) {
        if (snsLambdaContainer == null) {
            snsLambdaContainer = new SnsLambdaContainer(SnsConfig.class);
        }

        snsLambdaContainer.handleRequest(input, context);
        return null;
    }


}
