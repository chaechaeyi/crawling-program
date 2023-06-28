package com.kia.backend.service.impl;

import com.kia.backend.constant.PatternConstant;
import com.kia.backend.service.MakeStringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * 문자열 추출 service implementation
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MakeStringServiceImpl implements MakeStringService {

    /**
     * 문자열 merge
     * @param targetString
     * @return
     */
    @Override
    public String getMergeString(String targetString) {
        Stream<Character> charStream = getCharFilter(targetString);
        return getCrossSort(charStream);
    }

    /**
     * 전처리 (알파벳,숫자 제외하고 모두 제거(replace)/distinct/sort 처리 된 char stream)
     * @param targetString
     * @return
     */
    public Stream<Character> getCharFilter(String targetString) {
        return targetString
                .replaceAll(PatternConstant.ALPHABET_NUMBERIC_PATTERN, "")
                .chars()
                .distinct()
                .sorted()
                .mapToObj(c -> (char) c);
    }

    /**
     * 알파벳, 숫자 크로스 정렬
     * @param charStream
     * @return
     */
    public String getCrossSort(Stream<Character> charStream) {
        StringBuilder uppercase = new StringBuilder();
        StringBuilder lowercase = new StringBuilder();
        StringBuilder digits = new StringBuilder();

        charStream.forEach(c -> {
            if (Character.isUpperCase(c)) {
                uppercase.append(c);
            } else if (Character.isLowerCase(c)) {
                lowercase.append(c);
            } else if (Character.isDigit(c)) {
                digits.append(c);
            }
        });

        int maxLength = Math.max(Math.max(uppercase.length(), lowercase.length()), digits.length());

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxLength; i++) {
            if (i < uppercase.length()) {
                result.append(uppercase.charAt(i));
            }
            if (i < lowercase.length()) {
                result.append(lowercase.charAt(i));
            }
            if (i < digits.length()) {
                result.append(digits.charAt(i));
            }
        }

        return result.toString();
    }
}