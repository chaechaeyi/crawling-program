# KIA 과제

## Environments
* Amazon Corretto Version 17.0.7
* Spring Boot 3.1.0
* Gradle
* lombok
* jsoup

## Process
### html filtering
```mermaid
sequenceDiagram
    actor User
    participant Crawling
    participant Filter
    User->>Crawling: 필터링 된 String 반환 요청, 크롤링 html 하나로 merge
    Note over User, Crawling: async방식으로 크롤링 진행(hyundai/kia/genesis)  
    Crawling->>Filter: html String filtering
    Note over Crawling, Filter: 알파벳,숫자만남기고 제거/char변환<br/>/중복 제거/오름차순으로 정렬/cross정렬 진행
    Filter->>User: filtering 된 최종 형태의 String을 반환
```
# 프로젝트 패키지 구성


# 프로젝트 테스트 코드 구성 

## 프로젝트 회고
