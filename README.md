# intellij

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Deploy Status](https://img.shields.io/badge/deploy-success-blue)
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-2.7-green)
![MariaDB](https://img.shields.io/badge/MariaDB-10.6-blue)
![License](https://img.shields.io/badge/license-MIT-blue)

인텔리제이 실습 프로젝트

---

## 배포 안내

-	백엔드: CloudType에 Spring Boot(포트 8989)와 MariaDB를 배포
-	프론트엔드: GitHub Pages로 정적 배포하거나, Spring Boot 내 정적 리소스로 통합 배포


배포 URL: [https://port-0-intellij-mc9wb5q9f4801ee0.sel5.cloudtype.app/](https://port-0-intellij-mc9wb5q9f4801ee0.sel5.cloudtype.app/)

---
## 현재 진행 상황

- Spring Security를 활용한 로그인 기능 UI 구현 완료 (로그인 화면 적용)
- 보안 설정 및 인증 처리 기능 추가 작업 진행 중
- 회원가입, 권한 관리 등 보안 기능 추가 예정

---



## 배포 및 빌드 관련 참고 사항

- 도커파일이 프로젝트 메인 루트에 위치해야 하며, 이로 인해 초기 빌드 지연이 발생했습니다.  
- `.dockerignore` 파일을 추가해 빌드 시 불필요한 파일을 제외하여 최적화를 진행했습니다.  
- 배포 과정에서는 서버 재시작 및 이미지 재빌드가 반드시 필요합니다.

---

## 라이선스

이 프로젝트는 [MIT 라이선스](./LICENSE) 하에 배포됩니다.

---

📌 요약 키워드
	•	프레임워크: Spring Boot
	•	템플릿 엔진: Thymeleaf
	•	개발 도구: IntelliJ IDEA
	•	배포 플랫폼: CloudType
	•	포트: 8989
	•	아키텍처: 통합 배포, 서버사이드 렌더링(SSR), MariaDB 연동


---

```
┌──────────────────────────────────────────────┐
│         CloudType - Spring Boot (포트 8989)  │
│                                              │
│  /templates  →  Thymeleaf 템플릿 (HTML)      │ ← 프론트엔드 (SSR)
│  /static     →  JS, CSS, 이미지              │
│                                              │
│  @Controller → Model 전달 및 렌더링          │ ← 백엔드 로직
└──────────────────────────┬───────────────────┘
                           │  DB 연결 (JDBC)
                           ▼
           ┌────────────────────────────┐
           │     CloudType - MariaDB    │
           └────────────────────────────┘
```

---

## 프로젝트 단계별 문서 (노션)

각 단계별 상세 개발 내용과 구현 방법은 아래 노션 문서를 참고해 주세요:

- [1단계: 개발 환경 구축 및 MariaDB 설치](https://iridescent-breakfast-50b.notion.site/2025-06-18-21675f0fde6c80768360cc0422164162?source=copy_link)  
- [2단계: JPA 연동 및 Thymeleaf 적용](https://iridescent-breakfast-50b.notion.site/2025-06-19-21775f0fde6c80e298a7dc4070bcb8e3?source=copy_link)  
- [3단계: 게시판 CRUD 및 검색 기능 구현](https://iridescent-breakfast-50b.notion.site/2025-06-20-21875f0fde6c807aa61fcc540e768fd1?source=copy_link)  
- [4단계: 유효성 검사, 페이징 처리 및 Cloudtype 배포](https://iridescent-breakfast-50b.notion.site/2025-06-23-21b75f0fde6c80b88fe1dc9327527fbb?source=copy_link)  
- [5단계: 추가 개선 작업 및 배포 테스트](https://iridescent-breakfast-50b.notion.site/2025-06-24-21b75f0fde6c805592e2ea345fa591c3?source=copy_link)  

---

## 보안 기능 개발 문서 (노션)

- [Spring Security 적용 및 로그인 화면 구현](https://iridescent-breakfast-50b.notion.site/2025-06-29-22275f0fde6c801ea5d3fc0f9ad06baf?source=copy_link)  
- (추후 회원가입, 권한 관리 등 추가 예정)

- [시큐리티 적용 완료 + 크롬측 오류 있음](https://iridescent-breakfast-50b.notion.site/2025-07-02-22275f0fde6c801ea5d3fc0f9ad06baf?source=copy_link)

> - Spring Security 적용 및 로그인 화면 구현 완료  
> - 크롬 개발자도구에서 발생하는 경고성 오류 존재 (실행에는 영향 없음, 추후 개선 예정)  
> - 작업 내용은 별도 브랜치에서 진행 후 메인 브랜치로 병합 예정

---

## 주요 변경 사항

- `WebSecurityConfig.java` 추가하여 Spring Security 기본 설정 적용
- 로그인 페이지 커스터마이징 및 Thymeleaf 연동 완료
- 기존 게시판 CRUD에 인증 및 권한 체크 기능 연동 예정

---
## 버전 관리 및 브랜치 전략

- `main` 브랜치는 안정된 배포용 코드 유지  
- 기능 개발 및 수정 작업은 별도 기능 브랜치(feature branch)에서 진행  
- 작업 완료 후 Pull Request를 통해 `main` 브랜치에 병합  
- 주요 변경 시점에 태그(tag)를 생성하여 버전 관리 중  

  
- `feature/security-login` 브랜치에서 Spring Security 적용 작업 진행 중  
- 시큐리티 적용 완료 및 크롬 개발자도구 경고성 오류 존재 (실행에는 영향 없음, 추후 개선 예정)  
- 작업 완료 후 메인 브랜치로 병합 예정


**문의 및 연락처**  
✉️ syuri5458@naver.com 
🌐 [포트폴리오 링크](https://yuriportfolio.com)
