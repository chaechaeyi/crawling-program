package com.kia.assignment.controller;

import com.kia.assignment.dto.FilterStringDto;
import com.kia.assignment.service.CrawlingService;
import com.kia.assignment.service.MakeStringService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 문자열 필터링 controller
 */
@RestController
@RequestMapping("/filter/string")
@RequiredArgsConstructor
public class FilterStringController {
    private final CrawlingService crawlingService;
    private final MakeStringService makeStringService;

    /**
     * 문자열 필터링
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(value = "com.kia.backend.controller.getFilterString", unless = "#result == null")
    public ResponseEntity<FilterStringDto> getFilterString() {
        String mergeHtml = crawlingService.getAllParallelCrawling();
        return ResponseEntity.ok().body(new FilterStringDto(HttpStatus.OK.value(), makeStringService.getFilterByString(mergeHtml)));
    }
}
