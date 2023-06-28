package com.kia.backend.service;

import com.kia.backend.constant.CrawlingSite;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@DisplayName("크롤링 테스트")
@SpringBootTest
class CrawlingServiceTest {
    @Autowired
    private CrawlingService crawlingService;


    @Test
    void getHtmlStringInCrawling() throws ExecutionException, InterruptedException {
        CompletableFuture<String> html = crawlingService.getHtmlStringInCrawling(CrawlingSite.KIA.getSiteUrl());
        System.out.println(html.get());
    }
}