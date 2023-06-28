package com.kia.backend.service;

import java.util.stream.Stream;

/**
 * 문자열 연산 service
 */
public interface MakeStringService {

    /**
     * 문자열 merge
     *
     * @param targetString
     * @return
     */
    String getMergeString(String targetString) ;

    /**
     * 전처리 (알파벳,숫자 제외하고 모두 제거(replace)/distinct/sort 처리 된 char stream)
     *
     * @param targetString
     * @return
     */
    Stream<Character> replaceDistinctSortCharStream(String targetString);

    /**
     * 알파벳, 숫자 크로스 정렬
     *
     * @param charStream
     * @return
     */
    String alphabetNumericCrossSort(Stream<Character> charStream);
}
