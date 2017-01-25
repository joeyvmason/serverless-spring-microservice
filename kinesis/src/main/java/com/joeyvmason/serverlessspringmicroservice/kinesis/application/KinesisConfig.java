package com.joeyvmason.serverlessspringmicroservice.kinesis.application;

import com.joeyvmason.serverlessspringmicroservice.core.application.CoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CoreConfig.class)
@ComponentScan(basePackages = {"com.joeyvmason.serverlessspringmicroservice.kinesis"})
public class KinesisConfig {



}
