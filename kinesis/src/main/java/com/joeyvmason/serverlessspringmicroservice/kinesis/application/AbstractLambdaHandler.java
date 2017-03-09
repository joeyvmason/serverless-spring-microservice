package com.joeyvmason.serverlessspringmicroservice.kinesis.application;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.ParameterizedType;

@SuppressWarnings("unused")
public class AbstractLambdaHandler<C extends AbstractLambdaContainer<I, O>, I, O> implements RequestHandler<I, O> {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractLambdaHandler.class);

    private Class<? extends AbstractLambdaContainer<I, O>> containerClass;
    private ApplicationContext applicationContext;

    @SuppressWarnings("unchecked")
    public AbstractLambdaHandler() {
        this.containerClass = (Class<? extends AbstractLambdaContainer<I, O>>) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
        LOG.info("Initializing LambdaHandler({}) with Container({})", getClass(), containerClass);
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public O handleRequest(I input, Context context) {
        try {
            LOG.info("Handling request");
            if (applicationContext == null) {
                LOG.info("ApplicationContext does not exist. Initializing with Config({})", KinesisConfig.class);
                applicationContext = new AnnotationConfigApplicationContext(KinesisConfig.class);
            }

            AbstractLambdaContainer<I, O> container = applicationContext.getBean(containerClass);
            LOG.info("Handling request with Container({})", container.getClass());
            return container.handleRequest(input, context);
        } catch (RuntimeException e) {
            LOG.error("Unable to process event", e);
            throw e;
        } catch (Throwable e) {
            LOG.error("Unable to process event", e);
            throw new RuntimeException(e);
        }
    }
}
