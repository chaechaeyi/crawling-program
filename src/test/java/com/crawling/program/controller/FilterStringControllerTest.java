package com.crawling.program.controller;

import com.crawling.program.service.CrawlingService;
import com.crawling.program.service.MakeStringService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("문자열 출력 api 컨트롤러 테스트")
@WebMvcTest(FilterStringController.class)
class FilterStringControllerTest {
    private final MockMvc mvc;
    @MockBean
    private MakeStringService makeStringService;
    @MockBean
    private CrawlingService crawlingService;

    FilterStringControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    @DisplayName("문자열 출력 api 컨트롤러 테스트 - response status, body json 검증, content type(json)")
    void givenTestData_whenGetFilterString_thenResponseCheck() throws Exception {
        // Given
        String expectedResult = "Aa0Bb1Cc2Dd3Ee4Ff5Gg6Hh7Ii8Jj9KkLlMmNnOoPpQqRrSsTtUuVvWwXxYy";
        String mergeHtml = crawlingService.getAllParallelCrawling();
        given(makeStringService.getFilterByString(mergeHtml)).willReturn(expectedResult);

        // When & Then
        mvc.perform(get("/filter/string"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.merge").exists())
                .andExpect(jsonPath("$.status", 200).exists());

       then(makeStringService).should().getFilterByString(mergeHtml);
    }

    @Test
    @DisplayName("문자열 출력 api 컨트롤러 테스트 - ehcache 테스트")
    void givenTestDataNothing_whenGetFilterString_thenApplyEhcacheCheck() throws Exception {
        // given
        int cacheTtlMillis = 5000 ;
        // when & then
        // 최초 호출 시 cache 적용 되지 않음
        long beforeTime, afterTime, noEhCacheTime , ehCacheTime;

        beforeTime = System.currentTimeMillis();
        mvc.perform(get("/filter/string")).andExpect(status().isOk());
        afterTime = System.currentTimeMillis();
        noEhCacheTime = afterTime - beforeTime;

        // 1회 호출 이후 ttl이 지나지 않았므로 cache 적용되어 응답이 더 빠름
        beforeTime = System.currentTimeMillis();
        mvc.perform(get("/filter/string")).andExpect(status().isOk());
        afterTime = System.currentTimeMillis();
        ehCacheTime = afterTime - beforeTime;
        assertThat(noEhCacheTime).isGreaterThan(ehCacheTime);

        // 1회 호출 이후 ttl이 지나지 않았므로 cache 적용되어 응답이 더 빠름
        beforeTime = System.currentTimeMillis();
        mvc.perform(get("/filter/string")).andExpect(status().isOk());
        afterTime = System.currentTimeMillis();
        ehCacheTime = afterTime - beforeTime;
        assertThat(noEhCacheTime).isGreaterThan(ehCacheTime);
    }

}