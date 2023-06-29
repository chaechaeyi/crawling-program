package com.kia.assignment.constant;

import lombok.Getter;

/**
 * 크롤링 대상 사이트
 */
public enum CrawlingSite {
    HYUNDAI("https://shop.hyundai.com", true),
    KIA("https://www.kia.com", true),
    GENESIS("https://www.genesis.com", true);

    @Getter private final String url;
    @Getter private final Boolean isUsed;

    CrawlingSite(String url, Boolean isUsed) {
        this.url = url;
        this.isUsed = isUsed;
    }
}
