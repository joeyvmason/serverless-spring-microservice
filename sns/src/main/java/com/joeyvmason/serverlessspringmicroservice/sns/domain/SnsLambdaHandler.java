package com.joeyvmason.serverlessspringmicroservice.sns.domain;


import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.joeyvmason.serverlessspringmicroservice.sns.application.AbstractLambdaHandler;

public class SnsLambdaHandler extends AbstractLambdaHandler<SnsLambdaContainer, SNSEvent, Void> {

}
