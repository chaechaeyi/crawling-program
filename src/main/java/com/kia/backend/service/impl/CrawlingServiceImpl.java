package com.kia.backend.service.impl;

import com.kia.backend.constant.CrawlingSite;
import com.kia.backend.constant.TimeConstant;
import com.kia.backend.service.CrawlingService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 크롤링 service implementation
 */
@Slf4j
@Service
public class CrawlingServiceImpl implements CrawlingService {

    /**
     * 대상 사이트의 html 추출
     *
     * @return
     */
    @Async(value = "crawlingTaskExecutor")
    @Override
    public CompletableFuture<String> getHtmlStringByCrawlingSite(String siteUrl) {
        return CompletableFuture.supplyAsync(() -> extractHtml(siteUrl));
    }

    /**
     * 모든 대상 사이트의 html 추출
     *
     * @return
     */
    @Override
    public String getHtmlStringByCrawlingSiteAll() {
        return Arrays.stream(CrawlingSite.values()).parallel().map((v) -> extractHtml(v.getSiteUrl())).collect(Collectors.joining());
    }

    /**
     * html 코드 추출
     *
     * @param siteUrl
     * @return
     */
    private String extractHtml(String siteUrl) {
        try {
            Document document = Jsoup.connect(siteUrl).timeout(TimeConstant.JSOUP_TIME_OUT_MILLIS).get();
            return document.html();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
