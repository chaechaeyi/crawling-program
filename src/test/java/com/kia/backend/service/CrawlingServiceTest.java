package com.kia.backend.service;

import com.kia.backend.constant.CrawlingSite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("크롤링 테스트")
@SpringBootTest
class CrawlingServiceTest {
    private final String htmlStartTag = "<!doctype html>";
    private final String htmlEndTag = "</html>";
    @Autowired
    private CrawlingService crawlingService;

    @Test
    @DisplayName("크롤링 테스트 - 정상 return 테스트")
    void givenTestDataNothing_whenCralingSite_thenGetHtmlStringCheck() {
        // given
        //String kiaSiteUrl = CrawlingSite.KIA.getSiteUrl();
        // when
        String html = crawlingService.getHtmlStringByCrawlingSiteAll();
        // then
        // html 시작 태그와 종료 태그 check
        assertThat(html).isNotNull().startsWith(htmlStartTag).endsWith(htmlEndTag);
        // html 시작 태그의 수가 크롤링 대상 사이트 수와 동일한지 check
        assertThat(CrawlingSite.values().length).isEqualTo(StringUtils.countOccurrencesOf(html, htmlStartTag));
    }

}