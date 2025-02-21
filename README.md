## 목차

### Release Note

1. [Release 1.0.0 (2024.01 ~ 2024.04) - 개발 후 출시](#release-100-202401--202404---개발-후-출시)
2. [Release 1.0.1 (2024.05) - 버그 수정](#release-101-202405---버그-수정)
3. [Release 1.1.0 (2024.07) - 서비스 개선](#release-110-202407---서비스-개선)
4. [Release 1.1.1 (2024.09) - 버그 수정](#release-111-202409---버그-수정)
5. [Release 1.2.0 (2024.10 ~ 2024.11) - 아키텍처 개선](#release-120-202410--202411---아키텍처-개선)
6. [Release 1.2.1 (2025.02 ~ 진행중) - 학과명 변경 및 신설 학과 대응](#release-121-202502--진행중---학과명-변경-및-신설-학과-대응)

Release 1.0.0 ~ 1.1.1의 소스 코드는 [해당 프로젝트](https://github.com/TeamDMU/DMU-BackEnd2)를 참고해주세요.

### 개발 경험

1. [대학교 홈페이지 스크래핑 로직 개발](#대학교-홈페이지-스크래핑-로직-개발-공지사항-학사일정-식단표)
2. [공지사항, 학사일정, 식단표 조회 API 개발](#공지사항-학사일정-식단표-조회-api-개발)
3. [CI/CD 파이프라인 구축](#cicd-파이프라인-구축)
4. [Java에서 Kotlin으로 변경](#java에서-kotlin으로-변경)

### 개선 경험

1. [API 리팩토링 (Restful API 적용)](#api-리팩토링-restful-api-적용)
2. [알림 설정 API 성능 개선](#알림-설정-api-성능-개선)
3. [단일 모듈에서 멀티 모듈을 적용하여 아키텍처를 변경](#단일-모듈에서-멀티-모듈을-적용하여-아키텍처를-변경)
4. [API 서버와 Admin 서버 분리](#api-서버와-admin-서버-분리)
5. [세컨더리 인덱스를 추가하여, 쿼리 성능 최적화](#세컨더리-인덱스를-추가하여-쿼리-성능-최적화)

### 트러블 슈팅

1. [식단표를 불러오지 못하는 문제](https://gijung00.notion.site/24-04-12-86671c8432714061912a9ca2032aff5e?pvs=4)
2. [푸시 알림 24건 오전송](https://gijung00.notion.site/24-05-11-24-2edb7b1342b4495a9217c9252a8923ab?pvs=4)
3. [FCM 토큰 유실](https://gijung00.notion.site/24-05-12-FCM-0d74ad8a6d8e48b1ad665e10c716d43d?pvs=4)
4. [푸시 알림이 전송되지 않는 문제](#푸시-알림이-전송되지-않는-문제)
5. [당일이 아닌 다음날 푸시 알림이 전송되는 문제](#당일이-아닌-다음날-푸시-알림이-전송되는-문제)

### 블로그 포스팅

1. [서버 앞으로의 개선 방향]([https://rlwnd2577.tistory.com/entry/DMforU-서버-앞으로의-개선-방향](https://rlwnd2577.tistory.com/entry/DMforU-%EC%84%9C%EB%B2%84-%EC%95%9E%EC%9C%BC%EB%A1%9C%EC%9D%98-%EA%B0%9C%EC%84%A0-%EB%B0%A9%ED%96%A5))
2. [Kotlin + Spring / Multi-Module 적용]([https://rlwnd2577.tistory.com/entry/DMforU-Kotlin-Spring-Mutli-Module-적용](https://rlwnd2577.tistory.com/entry/DMforU-Kotlin-Spring-Mutli-Module-%EC%A0%81%EC%9A%A9))
3. [Jnuit5, Mocktito 테스트 코드 작성]([https://rlwnd2577.tistory.com/entry/DMforU-Jnuit5-Mocktito-테스트-코드-작성](https://rlwnd2577.tistory.com/entry/DMforU-Jnuit5-Mocktito-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%BD%94%EB%93%9C-%EC%9E%91%EC%84%B1))
4. [테스트 커버리지 관리 및 테스트 자동화]([https://rlwnd2577.tistory.com/entry/DMforU-테스트-커버리지-관리-및-테스트-자동화](https://rlwnd2577.tistory.com/entry/DMforU-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%BB%A4%EB%B2%84%EB%A6%AC%EC%A7%80-%EA%B4%80%EB%A6%AC-%EB%B0%8F-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%9E%90%EB%8F%99%ED%99%94))
5. [Jenkins CI / CD 구축]([https://rlwnd2577.tistory.com/entry/DMforU-Multi-Module-프로젝트-Jenkins-CI-CD-구축](https://rlwnd2577.tistory.com/entry/DMforU-Multi-Module-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-Jenkins-CI-CD-%EA%B5%AC%EC%B6%95))
6. [Prometheus + Grafana 모니터링 시스템 구축]([https://rlwnd2577.tistory.com/entry/DMforU-Prometheus-Grafana-모니터링-시스템-구축](https://rlwnd2577.tistory.com/entry/DMforU-Prometheus-Grafana-%EB%AA%A8%EB%8B%88%ED%84%B0%EB%A7%81-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EA%B5%AC%EC%B6%95))
7. [FCM 메시지 전송에 SQS 활용]([https://rlwnd2577.tistory.com/entry/DMforU-FCM-메시지-전송에-SQS-활용](https://rlwnd2577.tistory.com/entry/DMforU-FCM-%EB%A9%94%EC%8B%9C%EC%A7%80-%EC%A0%84%EC%86%A1%EC%97%90-SQS-%ED%99%9C%EC%9A%A9))
8. [Release 1.2.0 배포 완료]([https://rlwnd2577.tistory.com/entry/DMforU-Release-120-배포-완료](https://rlwnd2577.tistory.com/entry/DMforU-Release-120-%EB%B0%B0%ED%8F%AC-%EC%99%84%EB%A3%8C))
9. [MySQL 세컨더리 인덱스를 활용한 성능 개선]([https://rlwnd2577.tistory.com/entry/DMforU-공지사항-테이블-인덱스를-활용한-성능-개선](https://rlwnd2577.tistory.com/entry/DMforU-%EA%B3%B5%EC%A7%80%EC%82%AC%ED%95%AD-%ED%85%8C%EC%9D%B4%EB%B8%94-%EC%9D%B8%EB%8D%B1%EC%8A%A4%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-%EC%84%B1%EB%8A%A5-%EA%B0%9C%EC%84%A0))

---

## Release 1.0.0 (2024.01 ~ 2024.04) - 개발 후 출시

기술 스택: Java 17, Spring Boot, Spring JPA, MySQL, Redis, FCM, AWS(EC2, RDS)

### 대학교 홈페이지 스크래핑 로직 개발 (공지사항, 학사일정, 식단표)

- Jsoup 라이브러리를 활용하여 정적 스크래핑 로직을 개발
- 정적 스크래핑을 선택한 이유
  - 비용 효율성: 동적 스크래핑은 브라우저 렌더링을 필요로 하여 리소스와 실행 속도에 부담이 큼 반면, 정적 스크래핑은 HTML 소스를 직접 파싱하여 빠르고 서버 부담이 적음
  - 데이터 특성: 서버에서 제공하는 정적 HTML로 충분히 처리 가능하여 동적 스크래핑의 필요성 없음

### 공지사항, 학사일정, 식단표 조회 API 개발

---

## Release 1.0.1 (2024.05) - 버그 수정

### 푸시 알림이 전송되지 않는 문제

원인: Redis에 사용자의 푸시 알림 토큰을 저장했으나 TTL 설정이 되어 있었고, 토큰 갱신 API가 없었던 상황으로 토큰이 만료됨

해결 방법:

- Redis TTL을 1개월로 설정한 것을 확인하고, 토큰의 TTL을 해제하여 문제 해결
- 출시 직후라 문제는 개발자 토큰에서만 발생했으며, 사용자 토큰에는 영향을 미치지 않음

개선 사항: FCM 푸시 알림 관련 동료 간 커뮤니케이션 부족으로 발생한 문제였음 이를 해결하기 위해 회의 내용을 문서화하여 향후 커뮤니케이션 문제를 방지함

---

## Release 1.1.0 (2024.07) - 서비스 개선

기술 스택: 1.0.0 버전과 동일

### API 리팩토링 (Restful API 적용)

기존의 [POST]  /department/v1/dmu/updateDepartment에서 [PUT] /api/v1/subscribe/department와 같은 RESTful한 방식으로 리팩토링하여 멱등성과 통일성을 보장

### 알림 설정 API 성능 개선

문제 상황

- 기존 FCM Topic API를 사용하여 각 Topic에 토큰을 저장했지만, 1초의 응답 시간이 걸리는 문제를 확인
- 알림 설정을 위한 20개의 키워드에 대해 최대 20초가 소요되었음

해결 방법

- FCM Topic API를 사용하지 않고, MySQL에서 알림 관련 정보(Token, 학과, 키워드 등)를 관리하도록 변경
  - FCM Topic API를 비동기로 처리하는 방법도 고려했지만, 다음과 같은 상황을 고려함
  - 사용자가 반복적으로 알림을 껐다 켰다 할 경우, 반복하면 단기간에 너무 많은 API를 호출이 발생할 수 있는 문제가 있음

결론

- 알림 설정 API 응답 시간을 127ms로 개선
- 푸시 알림 설정 정보를 서버에서 관리함으로써 확장성(특정 사용자 또는 전체 사용자 푸시 알림 전송)을 고려한, 유연한 환경으로 전환됨

---

## Release 1.1.1 (2024.09) - 버그 수정

### 당일이 아닌 다음날 푸시 알림이 전송되는 문제

원인: 공지사항을 스크랩핑 하는 로직은 오전 10시와 오후 5시 총 2번 작동하게 되어있었는데, 퇴근 시간 이후에 공지사항을 업로드 하는 종종 일이 발생

해결방법: 스크래핑 로직을 6시에 동작하도록 변경

하지만, 이는 근본적인 해결책이 아니라고 판단하였고, 추후 Release 1.2.0에서 10분 단위로 오전 9시부터 오후 7시까지 주기적으로 스크래핑하도록 변경함

---

## Release 1.2.0 (2024.10 ~ 2024.11) - 아키텍처 개선

기술 스택: Kotlin, Spring Boot, Spring JPA, MySQL, MongoDB, Docker, Jenkins, FCM, AWS (EC2, RDS, SQS, Lambda), Prometheus, Grafana

### Java에서 Kotlin으로 변경

- Null 안전성과 코드 가독성을 위해 Java에서 Kotlin으로 리팩토링
- 개인적으로 Kotlin을 주 언어로 사용하기 위한 학습 목적도 있었음

### 단일 모듈에서 멀티 모듈을 적용하여 아키텍처를 변경

- 클린 아키텍처를 기반으로 Presentation, Domain, Infrastructure 모듈로 분리하여 확장성과 유지보수성을 향상
- 스크래핑 로직도 독립적인 모듈로 분리하여 각 계층 간 의존성을 최소화

### API 서버와 Admin 서버 분리

- Spring Batch 대신, 주기적인 스크래핑과 푸시 알림 전송 로직을 Admin 서버로 분리하여 관리
- 트래픽 증가 시 스케일 아웃을 고려한 분리

### FCM 의존성 제거 및 푸시 알림 부하 분산

- SQS와 Lambda를 사용하여 푸시 알림 부하를 분산시키고, FCM 의존성 제거

### CI/CD 파이프라인 구축

- Jenkins를 이용하여 CI/CD 파이프라인을 구축하여 자동화된 배포 프로세스 구현

### 세컨더리 인덱스를 추가하여, 쿼리 성능 최적화

- 세컨더리 인덱스와 쿼리 성능 최적화 작업을 통해 시스템의 응답 속도 개선

---

## Release 1.2.1 (2025.02 ~ 진행중) - 학과명 변경 및 신설 학과 대응

### 학과명 변경에 따른 API 요청 값 대응

- 구 버전 애플리케이션을 사용하고 있는 사용자를 가정하였음
- 애플리케이션 버전에 상관없이 API를 제공하기 위해 학과명을 핸들링 하도록 수정함
  - 추후, 신 버전으로 전부 이전 될 것이기 때문에, 구 학과명을 새로운 학과명으로 변경하는 유틸 클래스를 추가

### 신설 학과 대응

- 신설 학과 공지사항의 스크래핑 로직을 추가 작성