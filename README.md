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

**문의 및 연락처**  
✉️ syuri5458@naver.com 
🌐 [포트폴리오 링크](https://yuriportfolio.com)
