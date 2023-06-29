package com.kia.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("문자열 생성 테스트")
@SpringBootTest
class MakeStringServiceTest {
    @Autowired
    private MakeStringService makeStringService;
    @Test
    @DisplayName("문자열 생성 테스트 - 문자열 필터링 테스트 (전처리 & 교차정렬) ")
    void givenTestData_whenGetFilterByString_thenResultCheck() {
        // given
        String targetString = "A23****<12222aaaaBCDdddddefghIilmtv123";
        // when
        String result = makeStringService.getFilterByString(targetString);
        // then
        String expectedResult = "Aa1B2C3DdefghIilmtv";
        // 희망하는 결과 값과 실제 연산되여 나온 결과 값이 동일해야 함
        assertThat(expectedResult).isEqualTo(result);
    }
    @Test
    @DisplayName("문자열 생성 테스트 - 문자열 전처리 테스트 (숫자,알파벳만 남기고 제거/중복제거/오름차순정렬)")
    void givenTestData_whenGetFilterCharByString_thenPreProcessCheck() {
        // given
        String targetString = "A23****<12222aaaaBCDdddddefghIilmtv123";
        // when
        Stream<Character> characterStream = makeStringService.getFilterCharByString(targetString).mapToObj(value -> (char) value);
        // then
        String expectedResult = "123ABCDIadefghilmtv";
        String result = characterStream.map(character -> character.toString()).collect(Collectors.joining());
        // 희망하는 결과 값과 실제 연산되여 나온 결과 값이 동일해야 함
        assertThat(expectedResult).isEqualTo(result);
    }

    @Test
    @DisplayName("문자열 생성 테스트 - 교차 정렬이 제대로 되었는지 확인")
    void givenTestData_whenGetCrossSort_thenCrossSortCheck() {
        // given
        String targetString = "AaBCDdefghIilmtv123";
        IntStream targetCharStream = makeStringService.getFilterCharByString(targetString);
        // when
        String result = makeStringService.getCrossSortByIntStream(targetCharStream);
        // then
        String expectedResult = "Aa1B2C3DdefghIilmtv";
        // 희망하는 결과 값과 실제 연산되여 나온 결과 값이 동일해야 함
        assertThat(expectedResult).isEqualTo(result);
    }
}