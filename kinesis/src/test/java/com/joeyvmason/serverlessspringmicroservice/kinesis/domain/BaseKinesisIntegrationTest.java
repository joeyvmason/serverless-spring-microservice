package com.joeyvmason.serverlessspringmicroservice.kinesis.domain;

import com.joeyvmason.serverlessspringmicroservice.core.application.BaseCoreIntegrationTest;
import com.joeyvmason.serverlessspringmicroservice.kinesis.application.KinesisConfig;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {KinesisConfig.class, KinesisIntegrationTestConfig.class})
public class BaseKinesisIntegrationTest extends BaseCoreIntegrationTest {


}