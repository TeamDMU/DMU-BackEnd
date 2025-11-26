# DMU-BackEnd
동양미래대학교 홈페이지의 정보들을 활용하여 더 나은 편리성을 제공하기 위해 만들어진 API 서버입니다. <br>

## 목차
[1. 주요 기능](#주요-기능) <br>
[2. 아키텍처](#아키텍처) <br>
[3. 기술스택](#기술스택) <br>

## 주요 기능
- 원하는 학과, 키워드에 해당하는 대학 공지를 알림으로 받아볼 수 있습니다.
  - 각각의 알림은 ON / OFF 할 수 있습니다.
  - 키워드는 총 20개가 존재합니다.
- 학사 일정, 식단표 정보를 확인할 수 있습니다.
- 학교, 대학 공지사항을 검색 및 확인할 수 있습니다.

## 아키텍처
### Infrastructure
<img width="1000" alt="DMforU 아키텍처" src="https://github.com/user-attachments/assets/dd70b92a-f255-42d6-93e3-9c7a6ff20688">

### API Project
<img width="700" alt="DMforU 아키텍처" src="https://github.com/user-attachments/assets/96102a3a-18e2-4667-ab6b-fc93df4e7831">

### Admin Project
<img width="700" alt="DMforU 아키텍처" src="https://github.com/user-attachments/assets/4e92e493-3510-40c3-8ec7-b147378190b3">

## 기술스택
### Backend
- **언어 및 프레임워크**: Kotlin 1.9.25, Spring Boot 3.3.4
- **데이터 처리**: Spring Data JPA
- **데이터베이스**: MySQL 8.0.35, MongoDB 7.0.15
- **서버리스 컴퓨팅**: AWS Lambda
- **테스트**: JUnit5

### Infrastructure
- **컨테이너화**: Docker
- **CI/CD**: GitActions, Jenkins
- **클라우드**: AWS EC2,RDS,SQS

### Monitoring
- **모니터링 도구**: Prometheus, Grafana

### Test Coverage
- **테스트 커버리지**: JaCoCo, Codecov