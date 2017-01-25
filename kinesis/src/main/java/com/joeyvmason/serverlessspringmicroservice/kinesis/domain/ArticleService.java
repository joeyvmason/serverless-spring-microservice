package com.joeyvmason.serverlessspringmicroservice.kinesis.domain;

import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import org.springframework.stereotype.Component;

@Component
public class ArticleService {

    public void processKinesisEvent(KinesisEvent kinesisEvent) {

    }
}
