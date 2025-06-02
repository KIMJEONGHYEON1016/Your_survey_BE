# 설문조사 생성/응답/통계 서비스

## 🔗 배포 주소  
[https://your-survey.xyz](https://your-survey.xyz)

---

## 📝 프로젝트 소개

- 설문지를 생성하고 응답을 받을 수 있으며, 결과를 실시간으로 확인할 수 있는 웹 기반 설문 서비스입니다.
- 프론트엔드와 백엔드를 분리하여 각각 **Next.js(React)** 와 **Spring Boot**로 개발했습니다.
- 1인 개발 프로젝트로, 로그인/회원가입 없이 누구나 접근할 수 있습니다.

---

## 🛠 개발 기간
- **2025.02.10 ~ 2025.04.18 (약 2개월)**

---

## ⚙️ 기술 스택

| 영역       | 기술 |
|------------|------|
| Frontend   | Next.js, React |
| Backend    | Spring Boot, JPA, Oracle DB, REST API |
| DevOps     | AWS EC2, Jenkins, PM2, Nginx, Docker (Oracle XE) |
| Analytics  | Firebase Analytics |
| SEO        | next-sitemap, robots.txt, Google Search Console |

---

## ✅ 주요 기능

1. **설문 생성**
    - 설문 제목과 여러 개의 질문(객관식/단답형) 추가 가능
    - 질문별 옵션 추가/삭제, 복수선택 여부 설정 기능 포함
    - 설문 생성 완료 시 고유 토큰 기반 URL 자동 생성

2. **설문 응답**
    - 설문 URL로 누구나 접근 가능 (회원가입 불필요)
    - 객관식은 라디오 버튼 또는 체크박스로 응답
    - 단답형은 자유 입력

3. **응답 결과 확인**
    - 제출된 응답 데이터를 바탕으로 실시간 통계 제공
    - 차트 라이브러리를 활용한 막대/도넛형 시각화 제공

4. **CI/CD 자동 배포**
    - Jenkins에서 GitHub Webhook과 연동해 `push` 시 자동 빌드 및 배포
    - 백엔드는 PM2를 통해 `.jar` 파일 실행 및 무중단 재시작 처리
    - 프론트엔드는 `next build → next start`로 빌드 후 PM2 실행
    - Nginx로 프론트/백 분기 리버스 프록시 설정 및 `.xyz` 도메인 연동

5. **사용자 분석 및 SEO 최적화**
    - Firebase Analytics로 실시간 페이지 뷰 및 사용자 행동 분석
    - `next-sitemap`을 사용해 `sitemap.xml`, `robots.txt` 자동 생성
    - Google Search Console에 사이트맵 제출 → 검색 색인 처리 완료

