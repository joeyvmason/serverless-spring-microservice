package com.joeyvmason.serverlessspringmicroservice.kinesis.application;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;

@SuppressWarnings("unused")
public class KinesisLambdaHandler implements RequestHandler<KinesisEvent, Void> {

    // Just used by Amazon

    private KinesisLambdaContainer kinesisLambdaContainer;

    @Override
    public Void handleRequest(KinesisEvent input, Context context) {
        if (kinesisLambdaContainer == null) {
            kinesisLambdaContainer = new KinesisLambdaContainer(KinesisConfig.class);
        }

        kinesisLambdaContainer.handleRequest(input, context);
        return null;
    }


}
