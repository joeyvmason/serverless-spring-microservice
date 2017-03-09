package com.joeyvmason.serverlessspringmicroservice.kinesis.application;

import com.amazonaws.services.lambda.runtime.Context;

public interface AbstractLambdaContainer<I, O> {

    O handleRequest(I input, Context context) throws Exception;

}
