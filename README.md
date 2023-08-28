# 크롤링 서비스 간단 프로젝트 
(특정 웹사이트를 크롤링한 데이터를 요구 조건에 맞추어 Json 형태로 출력)


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
    actor Server
    participant Crawling
    participant Filter
    Server->>Crawling: 필터링 된 String 반환 요청, 크롤링 html 하나로 merge
    Note over Server, Crawling: 병렬 크롤링 진행(hyundai/kia/genesis)  
    Crawling->>Filter: html String filtering
    Note over Crawling, Filter: 알파벳,숫자만남기고 제거<br/>/중복 제거(대소문자 구분)<br/>/오름차순으로 정렬<br/>/cross정렬 진행
    Filter->>Server: filtering 된 최종 형태의 String을 반환
```


## 프로젝트 패키지 구성
* src.main.javacom.crawling.assignment
    * controller
    * constant
    * dto
    * service
* src.test.java.com.crawling.assignment
    * controller
    * service


## 프로젝트 테스트 코드 구성 
1. FilterStringControllerTest.java - 문자열 출력 api 컨트롤러 테스트
    * givenTestData_whenGetFilterString_thenResponseCheck
      
      response status, body json 검증, content type(json)
      
    * givenTestDataNothing_whenGetFilterString_thenApplyEhcacheCheck
      
      ehcache 테스트
      
3. CrawlingServiceTest.java - 크롤링 테스트
    * givenTestDataNothing_whenGetAllParallelCrawling_thenGetHtmlStringCheck
      
      정상 html merge return 테스트
      
    * givenTestData_whenGetCrawlingByUrlAndGetAllParallelCrawling_thenTimeCheck
      
      크롤링 대상 하나에 소모되는 시간과 전체 크롤링에 소모 시간 테스트
   
    * givenTestData_whenGetCrawlingByUrlAndGetAllAsyncCrawling_thenExecuteIgnoreOrderCheck

      순차적으로 처리하지 않는지 테스트
      
4. MakeStringServiceTest.java - 문자열 생성 테스트
    * givenTestData_whenGetFilterByString_thenResultCheck
      
      문자열 필터링 테스트 (전처리 & 교차정렬)
      
    * givenTestData_whenGetFilterCharByString_thenPreProcessCheck
      
      문자열 전처리 테스트 (숫자,알파벳만 남기고 제거/중복제거/오름차순정렬)

    * givenTestData_whenGetCrossSort_thenCrossSortCheck

      교차 정렬이 제대로 되었는지 확인
         

## 프로젝트 회고
* controller, service에서 기능별 테스트를 작성 및 Postman을 사용하여 기능 검증을 하였습니다. 
* merge 된 html 문자열의 대상군을 줄이고자

  (1) pattern으로 digit, alphabet만 남기고 제거

  (2) 대소문자 구분을 위해 char형으로 변경하여 distinct처리

  (3) 전처리가 모두 된 상태에서 오름차순

* 대상 데이터가 적고 민감도가 적은 데이터라 판단하여  redis cache 대신 ehcache를 적용하였습니다.  
* jsoup를 사용한 이유

  편리성 : 현재 프로젝트에서는 사용하지 않았지만 원하는 요소를 선택하여 추출 할 수있음음
  
  계속적인 버전관리 : 최근 버전은 2023/4/29 업데이트 된 이력이 있고 2010년 만들어진 이후로 2~4개월 버전관리가 이루어 지고 있음.
  
  https://mvnrepository.com/artifact/org.jsoup/jsoup
      
