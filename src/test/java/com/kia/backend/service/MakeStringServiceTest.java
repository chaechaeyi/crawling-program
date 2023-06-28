package com.kia.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@DisplayName("문자열 추출 테스트")
@SpringBootTest
class MakeStringServiceTest {

    @Autowired
    private MakeStringService makeStringService;

    @Test
    void extractStringInHtml() {
       // String test = makeStringService.getMergeString();
        //System.out.println(test);


    }
}