package com.kia.backend.service;

/**
 * 문자열 연산 service
 */
public interface MakeStringService {

    /**
     * html 코드 내에서 문자열 추출
     * @return
     */
    String getMergeString(String targetString) ;
}
