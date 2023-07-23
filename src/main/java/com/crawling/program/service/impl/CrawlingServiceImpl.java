package com.crawling.program.service.impl;

import com.crawling.program.constant.TimeOut;
import com.crawling.program.service.CrawlingService;
import com.crawling.program.constant.CrawlingSite;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.joining;

/**
 * 크롤링 service implementation
 */
@Slf4j
@Service
public class CrawlingServiceImpl implements CrawlingService {
    ExecutorService executor = Executors.newFixedThreadPool(Math.min(CrawlingSite.values().length, 20), runnable -> {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        return thread;
    });

    /**
     * 대상 사이트의 html 추출 (async)
     * @return
     */
    @Override
    public String getAllParallelCrawling() {
        List<CrawlingSite> crawlingSiteList =  List.of(CrawlingSite.values());
        return  crawlingSiteList.stream()
                .map(v ->
                        CompletableFuture.supplyAsync(() ->
                                        getHtml(v.getUrl()), executor)
                                .orTimeout(TimeOut.ASYNC_THREAD_MILLIS, TimeUnit.MILLISECONDS))
                .map(CompletableFuture::join)
                .parallel()
                .collect(joining());
    }

    /**
     * 대상 사이트 html 추출
     * @return
     */
    @Override
    public String getCrawlingByUrl(String url) {
        return getHtml(url);
    }

    /**
     * html 코드 추출
     * @param siteUrl
     * @return
     */
    private String getHtml(String siteUrl) {
        try {
            Document document = Jsoup.connect(siteUrl).timeout(TimeOut.JSOUP_MILLIS).get();
            return document.html();
        } catch (IOException e) {
            log.error("extractHtml exception e : {}", e);
            return "";
        }
    }
}
