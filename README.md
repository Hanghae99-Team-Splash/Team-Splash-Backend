# 🎊항해99 7주차 클론코딩 <13조>🎊
📆2021.12.13 ~ 2021.12.18
</br>

# Team Splash
웹사이트 unsplash 클론 코딩 =>
<a href="https://unsplash.com/">Unsplash 웹사이트</a>  

</br>
</br>

## 구성원 및 역할👫

### **Front-end**
- 정주혜:
- 이준명:

프론트 깃헙 링크 => 
<a href="https://github.com/Hanghae99-Team-Splash/Team-Splash-Frontend">바로가기</a>

</br>


### **Back-end**
- 이현범: 회원가입, JWT 로그인 기능 구현, 태그화, 좋아요, 카카오톡 로그인, Spring Security 사용
- 임전혁: S3 게시물 기능 구현, 조회수 기능 구현, 테스트코드 작성, EC2배포, CORS처리

백엔드 깃헙 링크 => 
<a href="https://github.com/Hanghae99-Team-Splash/Team-Splash-Backend">바로가기</a>

</br>
</br>

---
</br>

## 🎞프로젝트 시연 영상🎞




</br>
</br>

## 🛠TECH STACK🛠

### Back-End

<img height="28" src="https://img.shields.io/badge/github-181717?style=flat&logo=github&logoColor=white"></a>&nbsp;
<img height="28" src="https://img.shields.io/badge/MySQL-005C84?style=flat&logo=mysql&logoColor=white"></a>&nbsp;
<img height="28" src="https://img.shields.io/badge/Springboot-47?style=flat&logo=Springboot&logoColor=white"/></a>&nbsp;
<img height="28" src="https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white"/></a>&nbsp;
<img height="28" src="https://img.shields.io/badge/JWT-000000?style=flat&logo=JSON%20web%20tokens&logoColor=white"></a>&nbsp;  
<img height="28" src="https://img.shields.io/badge/Swagger-85EA2D?style=flat&logo=Swagger&logoColor=white"></a>&nbsp;
<img height="28" src="https://img.shields.io/badge/gradle-02303A?style=flat&logo=gradle&logoColor=white"></a>&nbsp;
<img height="28" src="https://img.shields.io/badge/Amazon_AWS-FF9900?style=flat&logo=amazonaws&logoColor=white"></a>&nbsp;
<img height="28" src="https://img.shields.io/badge/Notion-000000?style=flat&logo=notion&logoColor=white"></a>&nbsp;


### Front-End
<div>
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
  <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black">
  <img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
  <img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=black">
  <img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=AmazonAWS&logoColor=white">
  <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">  
  
</div>
</br>
</br>

## ⚙주요 기능⚙
**로그인/회원가입**
  - 이메일 중복확인 기능
  - 회원가입시 Email, 비밀번호 유효성 검사
  - 빈칸 금지
  </br>
  
**메인페이지**
  * 로그인한 유저이름 출력
  * 게시글 목록 출력
    - 키워드 클릭 시 해당 카테고리의 출력
    - 카드별 이미지, Title, 작성자, 조회수 표시
  * 카테고리 별 게시글 출력(필터)
    - 키워드 클릭 시 해당 카테고리의 출력
  * 상세페이지 이동
    - 카드 클릭 시 해당 상세페이지 이동
  </br>
  
**게시글 작성 페이지**
  * 사진 업로드
  * 빈칸 메세지
  </br>
  
**상세페이지**
  * 게시글 상세내용 출력
  * 댓글기능
    - 로그인한 사용자만 댓글 작성가능
</br>

**마이페이지**
  * 로그인한 사용자의 게시물 확인 가능
</br>
</br>

## 📅Entity 다이어그램
<img width="723" alt="스크린샷 2021-12-16 오후 10 03 58" src="https://user-images.githubusercontent.com/57126167/146377185-47f86312-386d-45ff-bcf8-9c4199de3769.png">
</br>
</br>


</br>
</br>

## 📜API TABLE
</br>
<img width="1124" alt="스크린샷 2021-12-16 오후 10 08 11" src="https://user-images.githubusercontent.com/57126167/146378022-6f242a9b-e370-4dff-8000-7d69d0edeb0e.png">
<img width="1123" alt="스크린샷 2021-12-16 오후 10 08 31" src="https://user-images.githubusercontent.com/57126167/146378049-f27bbf91-9711-4ff2-bd1f-c684fb1a124c.png">
<img width="1165" alt="스크린샷 2021-12-16 오후 10 09 30" src="https://user-images.githubusercontent.com/57126167/146378069-da9a9a43-0621-4bf0-be3a-ca32706e56dd.png">
<img width="1172" alt="스크린샷 2021-12-16 오후 10 09 44" src="https://user-images.githubusercontent.com/57126167/146378097-d6ccca1c-9670-4882-b928-05f1750ed74b.png">
<img width="1173" alt="스크린샷 2021-12-16 오후 10 09 54" src="https://user-images.githubusercontent.com/57126167/146378120-932153b1-c3aa-4aa9-b14b-4cfc82f1e061.png">
<img width="1252" alt="스크린샷 2021-12-16 오후 10 10 15" src="https://user-images.githubusercontent.com/57126167/146378123-d4f5ca3d-72a8-4a1e-959f-ccf7abd07778.png">
<img width="1001" alt="스크린샷 2021-12-16 오후 10 10 25" src="https://user-images.githubusercontent.com/57126167/146378127-c2a743c4-16c6-4648-9689-0d767578c45d.png">
<img width="1031" alt="스크린샷 2021-12-16 오후 10 10 39" src="https://user-images.githubusercontent.com/57126167/146378130-6188bd0b-f7d3-4ceb-897f-ded0351b6407.png">
</br>
</br>

## ✒Trouble Shooting
<details>
    <summary>
        1. CORS 정책으로 인한 접속문제
    </summary>
    <div markcown="1">
        cors필터를 스프링 시큐리티에 끼워 넣어서 해결
    </div>
</details>

<details>
    <summary>
        2. Spring Security와 JWT로 인한 카카오 로그인 문제
    </summary>
    <div markcown="1">
        카카오 강제로그인을 없애고 jwt 토큰 발급을 이용해 해결
    </div>
</details>


</br>
</br>
