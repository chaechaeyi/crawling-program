package com.crawling.program.service;

/**
 * 크롤링 service
 */
public interface CrawlingService {
    /**
     * 모든 대상 사이트의 html 추출 (async)
     * @return
     */
    String getAllParallelCrawling();

    /**
     * 대상 사이트 html 추출
     * @param url
     * @return
     */
    String getCrawlingByUrl(String url);


}
