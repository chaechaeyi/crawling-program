package com.kia.backend.service.impl;

import com.kia.backend.service.CrawlingService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * 크롤링 service implementation
 */
@Slf4j
@Service
public class CrawlingServiceImpl implements CrawlingService {

    /**
     * 사이트 크롤링을 통해 html 추출
     *
     * @return
     */
    @Async(value = "crawlingTaskExecutor")
    @Override
    public CompletableFuture<String> getHtmlStringInCrawling(String siteUrl) {
        return CompletableFuture.supplyAsync(() -> {
            Connection conn = Jsoup.connect(siteUrl).timeout(2000);
            try {
                Document document = conn.get();
                return document.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
