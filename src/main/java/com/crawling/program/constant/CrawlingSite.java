package com.crawling.program.constant;

import lombok.Getter;

/**
 * 크롤링 대상 사이트
 */
public enum CrawlingSite {
    NAVER("https://www.naver.com", true),
    DAUM("https://www.daum.net/", true);

    @Getter private final String url;
    @Getter private final Boolean isUsed;

    CrawlingSite(String url, Boolean isUsed) {
        this.url = url;
        this.isUsed = isUsed;
    }
}
