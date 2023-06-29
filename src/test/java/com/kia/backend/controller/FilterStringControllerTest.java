package com.kia.backend.controller;

import com.kia.backend.constant.CrawlingSite;
import com.kia.backend.dto.FilterStringDto;
import com.kia.backend.service.CrawlingService;
import com.kia.backend.service.MakeStringService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@DisplayName("문자열 출력 api 컨트롤러 테스트")
@WebMvcTest(FilterStringController.class)
class FilterStringControllerTest {
    private final MockMvc mvc;

    @MockBean private CrawlingService crawlingService;
    @MockBean  private MakeStringService makeStringService;

    FilterStringControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    @DisplayName("문자열 출력 api 컨트롤러 테스트 - body Object key/value 검증, 호출 성공여부, content type(json)")
    void givenTestData_whenGetAllAsyncCrawling_thenExcuteObjectCheck() throws Exception {
        // Given
        String anyString = "A23****<12222aaaaBCDdddddefghIilmtv123";
        String expectedResult = "Aa1B2C3DdefghIilmtv";
        given(makeStringService.getFilterByString(anyString)).willReturn(expectedResult);

        // When & Then
        mvc.perform(get("/filter/string"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print());
        then(makeStringService).should().getFilterByString(anyString());
    }

    @Test
    @DisplayName("문자열 출력 api 컨트롤러 테스트 - ehcache 테스트")
    void givenTestData_whenGetAllAsyncCdrawling_thenExcuteObjectCheck() {
        // given
        String kiaSiteUrl = CrawlingSite.KIA.getUrl();
        // when

        // then
        // html 시작 태그와 종료 태그 check
    }

}