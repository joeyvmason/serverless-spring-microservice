package com.joeyvmason.serverlessspringmicroservice.kinesis.domain;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.joeyvmason.serverlessspringmicroservice.kinesis.application.AbstractLambdaHandler;
import com.joeyvmason.serverlessspringmicroservice.kinesis.application.KinesisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KinesisEventHandler extends AbstractLambdaHandler<KinesisEvent, Void> {
    private static final Logger LOG = LoggerFactory.getLogger(KinesisEventHandler.class);

    public KinesisEventHandler() {
        super(KinesisConfig.class);
        LOG.info("KinesisEventHandler created");
    }

    @Override
    public Void handleRequestImpl(KinesisEvent input, Context context) {
        LOG.info("Processing Kinesis event");
        return null;
    }

//    public static void main(String[] args) {
//        LOG.info("Firing this bad boy up");
//        KinesisEventHandler kinesisEventHandler = new KinesisEventHandler();
//        kinesisEventHandler.handleRequest(null, null);
//    }
}
