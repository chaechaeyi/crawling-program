package com.kia.backend.service;

import java.util.stream.Stream;

/**
 * 문자열 연산 service
 */
public interface MakeStringService {

    /**
     * html 코드 내에서 문자열 추출
     * @return
     */
    String getMergeString(String targetString) ;

    /**
     * 전처리 (replace/distinct/sort처리 된 char stream)
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
