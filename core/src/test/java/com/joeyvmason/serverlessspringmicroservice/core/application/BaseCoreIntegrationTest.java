package com.joeyvmason.serverlessspringmicroservice.core.application;

import com.joeyvmason.serverlessspringmicroservice.core.domain.articles.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;

@ContextConfiguration(classes = {CoreConfig.class, CoreIntegrationTestConfig.class})
public class BaseCoreIntegrationTest extends AbstractTestNGSpringContextTests {
    private static final Logger LOG = LoggerFactory.getLogger(BaseCoreIntegrationTest.class);

    @Autowired
    private ArticleRepository articleRepository;

    @AfterMethod
    public void tearDown() throws Exception {
        LOG.info("Deleting all Articles");
        articleRepository.deleteAll();
    }
}
