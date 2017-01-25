package com.joeyvmason.serverlessspringmicroservice.kinesis.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.joeyvmason.serverlessspringmicroservice.core.application.CoreConfig;
import org.springframework.context.annotation.*;

@Configuration
@Import(CoreConfig.class)
@ComponentScan(basePackages = {"com.joeyvmason.serverlessspringmicroservice.kinesis"})
public class KinesisConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper;
    }

}
