package com.kia.assignment.service;

import com.kia.assignment.constant.CrawlingSite;
import com.kia.assignment.constant.TimeOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("크롤링 테스트")
@SpringBootTest
class CrawlingServiceTest {
    private final String htmlStartTag = "<!doctype html>";
    private final String htmlEndTag = "</html>";
    @Autowired
    private CrawlingService crawlingService;

    @Test
    @DisplayName("크롤링 테스트 - 정상 html merge return 테스트")
    void givenTestDataNothing_whenGetAllAsyncCrawling_thenGetHtmlStringCheck() {
        // given
        //String kiaSiteUrl = CrawlingSite.KIA.getUrl();
        // when
        String html = crawlingService.getAllAsyncCrawling();
        // then
        // html 시작 태그와 종료 태그 check
        assertThat(html).isNotNull().startsWith(htmlStartTag).endsWith(htmlEndTag);
        // html 시작 태그의 수가 크롤링 대상 사이트 수와 동일한지 check
        assertThat(CrawlingSite.values().length).isEqualTo(StringUtils.countOccurrencesOf(html, htmlStartTag));
    }

    @Test
    @DisplayName("크롤링 테스트 - 크롤링 대상 하나에 소모되는 시간과 전체 크롤링에 소모 시간 테스트")
    void givenTestData_whenGetCrawlingByUrlAndGetAllAsyncCrawling_thenTimeCheck() {
        // given CrawlingSite
        // when
        long beforeTime = System.currentTimeMillis();
        for (CrawlingSite crawlingSite : CrawlingSite.values()) {
            crawlingService.getCrawlingByUrl(crawlingSite.getUrl());
        }
        long afterTime = System.currentTimeMillis();
        long taskTime = (afterTime - beforeTime) / 1000;

        beforeTime = System.currentTimeMillis();
        crawlingService.getAllAsyncCrawling();
        afterTime = System.currentTimeMillis();
        long asyncTaskTime = (afterTime - beforeTime) / 1000;

        // then
        // async로 모든 사이트 크롤링 하는 시간이 건건이 모든 사이트 크롤링 하는 시간보다 작다.
        assertThat(taskTime).isGreaterThan(asyncTaskTime);

    }

    @Test
    @DisplayName("크롤링 테스트 - 병렬 처리 테스트 (시작 시점은 같고 종료시점이 다르므로 실행순서는 동일해야함) ")
    void givenTestData_whenAsyncWork_thenExcuteIgnoreOrderCheck() throws InterruptedException {
        // given
        List<CrawlingSite> crawlingSiteList =  List.of(CrawlingSite.values());

        // when
        List<String> taskList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String task = crawlingSiteList.stream()
                    .map(v ->
                            CompletableFuture.supplyAsync(() -> v.name()))
                    .map(CompletableFuture::join)
                    .parallel()
                    .collect(joining());
            taskList.add(task);
        }

        // then
        int sameCount = Collections.frequency(taskList, taskList.get(0));
        assertThat(taskList.size()).isEqualTo(sameCount);
    }

}