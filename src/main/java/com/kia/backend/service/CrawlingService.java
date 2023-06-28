package com.kia.backend.service;

import io.netty.util.concurrent.Future;

import java.util.concurrent.CompletableFuture;

/**
 * 크롤링 service
 */
public interface CrawlingService {

    /**
     * html String 추출
     * @param siteUrl
     * @return
     */
    CompletableFuture<String> getHtmlStringInCrawling(String siteUrl);
}
