package com.joeyvmason.serverlessspringmicroservice.kinesis.domain;


import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.joeyvmason.serverlessspringmicroservice.kinesis.application.AbstractLambdaHandler;

public class KinesisLambdaHandler extends AbstractLambdaHandler<KinesisLambdaContainer, KinesisEvent, Void> {

}
