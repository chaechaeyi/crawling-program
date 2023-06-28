package com.kia.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("문자열 생성 테스트")
@SpringBootTest
class MakeStringServiceTest {

    @Autowired
    private MakeStringService makeStringService;

    @Test
    @DisplayName("문자열 생성 테스트 - 문자열 merge 테스트")
    void givenTestData_whenMergeString_thenExcuteObjectCheck () {
        // String test = makeStringService.getMergeString();
        //System.out.println(test);
    }

    @Test
    @DisplayName("문자열 생성 테스트 - 문자열 전처리 테스트 (replace/중복제거/오름차순정렬)")
    void givenTestData_whenReplaceDistinctSortCharStream_thenPreProcessCheck() {
        // given
        String targetString = "AaBCDdefghIilmtv123";
    }

    @Test
    @DisplayName("문자열 생성 테스트 - 교차 정렬이 제대로 되었는지 확인")
    void givenTestData_whenAlphabetNumericCrossSort_thenCrossSortCheck() {
        // given
        String targetString = "AaBCDdefghIilmtv123";
        Stream<Character> targetCharStream = makeStringService.replaceDistinctSortCharStream(targetString);
        // when
        String result = makeStringService.alphabetNumericCrossSort(targetCharStream);
        // then
        String expectedResult = "Aa1B2C4DdefghIilmtv";
        assertThat(expectedResult).isEqualTo(targetString);
    }
}