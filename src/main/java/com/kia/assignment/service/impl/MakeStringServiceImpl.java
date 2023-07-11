package com.kia.assignment.service.impl;

import com.kia.assignment.constant.PatternType;
import com.kia.assignment.service.MakeStringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

import static com.kia.assignment.constant.ASCII.*;
import static java.util.stream.Collectors.*;

/**
 * 문자열 추출 service implementation
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MakeStringServiceImpl implements MakeStringService {
    /**
     * 문자열 처리
     * @param targetString
     * @return
     */
    @Override
    public String getFilterByString(String targetString) {
        if(targetString.isBlank()) return "";
        IntStream charStream = getFilterCharByString(targetString);
        return getCrossSortByIntStream(charStream);
    }

    /**
     * 전처리 (알파벳,숫자 제외하고 모두 제거(replace)/distinct/sort 처리 된 char stream)
     * @param targetString
     * @return
     */
    public IntStream getFilterCharByString(String targetString) {
        return targetString
                .replaceAll(PatternType.ALPHABET_DISIT_PATTERN, "")
                .chars()
                .distinct()
                .sorted();
    }

    /**
     * 알파벳, 숫자 크로스 정렬
     * @param targetIntStream
     * @return
     */
    public String getCrossSortByIntStream(IntStream targetIntStream) {
        List<Integer> charList = targetIntStream.mapToObj(i->i).collect(toList());

        // for문 돌아가면서 문자열 생성
        StringBuilder result = new StringBuilder();
        for (int i = 0; i<ALPHABET_COUNT; i++) {
            if(charList.contains(UPPERCASE_MIN+i)) result.append((char)(UPPERCASE_MIN+i));
            if(charList.contains(LOWERCASE_MIN+i)) result.append((char)(LOWERCASE_MIN+i));
            if(i<charList.size() && charList.get(i)<=DISIT_MAX) result.append((char)charList.get(i).intValue());
        }

        return result.toString();
    }
}