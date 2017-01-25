package com.joeyvmason.serverlessspringmicroservice.kinesis.application;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.joeyvmason.serverlessspringmicroservice.kinesis.domain.KinesisEventProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class KinesisLambdaContainer {

    private ApplicationContext applicationContext;

    public KinesisLambdaContainer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public KinesisLambdaContainer(Class... config) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(config);
        applicationContext.refresh();
        this.applicationContext = applicationContext;
    }

    public void handleRequest(KinesisEvent kinesisEvent, Context context) {
        KinesisEventProcessor kinesisEventProcessor = applicationContext.getBean(KinesisEventProcessor.class);
        kinesisEventProcessor.handleRequest(kinesisEvent, context);
    }

}
