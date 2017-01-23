package com.joeyvmason.serverlessspringmicroservice.core.domain.articles;


import com.joeyvmason.serverlessspringmicroservice.core.application.BaseCoreIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ArticleRepositoryTest extends BaseCoreIntegrationTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void shouldSaveArticle() {
        //given
        Article article = ArticleTestBuilder.valid().build();

        //when
        article = articleRepository.save(article);

        //then
        Article articleFromDB = articleRepository.findOne(article.getId());
        assertThat(articleFromDB).isEqualTo(article);
    }

}
