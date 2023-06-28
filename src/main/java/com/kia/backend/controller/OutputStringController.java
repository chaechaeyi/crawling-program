package com.kia.backend.controller;

import com.kia.backend.service.CrawlingService;
import com.kia.backend.service.MakeStringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 문자열 출력 controller
 */
@RestController
@RequestMapping("/string/output")
@RequiredArgsConstructor
public class OutputStringController {
    private final CrawlingService crawlingService;
    private final MakeStringService makeStringService;

    /**
     * html 코드 내에서 문자열 추출
     *
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getMergeStringInHtml() {
        String htmlString = crawlingService.getHtmlStringByCrawlingSiteAll();
        return new ResponseEntity<>(makeStringService.getMergeString(htmlString), HttpStatus.OK);
    }
}
