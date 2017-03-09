package com.joeyvmason.serverlessspringmicroservice.sns.application;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.joeyvmason.serverlessspringmicroservice.sns.domain.SnsEventProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SnsLambdaContainer {

    private ApplicationContext applicationContext;

    public SnsLambdaContainer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public SnsLambdaContainer(Class... config) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(config);
        applicationContext.refresh();
        this.applicationContext = applicationContext;
    }

    public void handleRequest(KinesisEvent kinesisEvent, Context context) {
        SnsEventProcessor kinesisEventProcessor = applicationContext.getBean(SnsEventProcessor.class);
        kinesisEventProcessor.handleRequest(kinesisEvent, context);
    }

}
