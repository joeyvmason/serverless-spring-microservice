package com.joeyvmason.serverlessspringmicroservice.kinesis.domain;

import com.joeyvmason.serverlessspringmicroservice.core.application.BaseCoreIntegrationTest;
import com.joeyvmason.serverlessspringmicroservice.sns.application.SnsConfig;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {SnsConfig.class, SnsIntegrationTestConfig.class})
public class BaseSnsIntegrationTest extends BaseCoreIntegrationTest {

}