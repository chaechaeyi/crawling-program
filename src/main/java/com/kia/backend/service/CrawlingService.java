package com.kia.backend.service;

import io.netty.util.concurrent.Future;

import java.util.concurrent.CompletableFuture;

/**
 * 크롤링 service
 */
public interface CrawlingService {

    /**
     * 대상 사이트의 html 추출
     * @return
     */
    CompletableFuture<String> getHtmlStringByCrawlingSite(String siteUrl);

    /**
     * 모든 대상 사이트의 html 추출
     * @return
     */
    String getHtmlStringByCrawlingSiteAll();
}
