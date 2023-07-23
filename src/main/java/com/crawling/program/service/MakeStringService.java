package com.crawling.program.service;

import java.util.stream.IntStream;

/**
 * 문자열 연산 service
 */
public interface MakeStringService {
    /**
     * 문자열 merge
     * @param targetString
     * @return
     */
    String getFilterByString(String targetString) ;

    /**
     * 전처리 (알파벳,숫자 제외하고 모두 제거(replace)/distinct/sort 처리 된 char stream)
     * @param targetString
     * @return
     */
    IntStream getFilterCharByString(String targetString);

    /**
     * 알파벳, 숫자 크로스 정렬
     * @param intStream
     * @return
     */
    String getCrossSortByIntStream(IntStream intStream);
}
