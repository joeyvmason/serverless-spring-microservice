package com.joeyvmason.serverlessspringmicroservice.kinesis.application;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public abstract class AbstractLambdaHandler<I, O> implements RequestHandler<I, O>  {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractLambdaHandler.class);

    private Class[] config;
    private AbstractLambdaHandler<I, O> abstractLambdaHandler;

    public AbstractLambdaHandler(Class... config) {
        this.config = config;
    }

    public abstract O handleRequestImpl(I input, Context context);

    @Override
    public O handleRequest(I input, Context context) {
        if (abstractLambdaHandler == null) {
            AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(config);
            abstractLambdaHandler = applicationContext.getBean(AbstractLambdaHandler.class);
        }

        return abstractLambdaHandler.handleRequestImpl(input, context);
    }
}
