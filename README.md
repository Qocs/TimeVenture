# 🕖 프로젝트 협업 도구 서비스 TimeVenture
![image](https://github.com/user-attachments/assets/16e73bb0-dc0e-46d4-bee7-86f7842eaa72)

## 👨‍💻 프로젝트 소개
협력 프로젝트를 진행하는 일원들이 서로의 task를 공유하고 진행 사항을 거시적으로 볼 수 있도록 돕는 협업 툴 서비스입니다.

해당 서비스를 이용함으로써 사용자들은 협업 과정에서 필요한 공유 작업을 손쉽게 할 수 있습니다.

Backend 5인 프로젝트로서 각자의 임무를 정하고, 역할에 맞게 협력함으로 진행하였습니다.

## 🕖 프로젝트 기간

2024.05.03 ~ 2024.06.10까지 진행 후 개인적으로 리팩토링하는 작업을 거쳤습니다.

## 🛠️ Spring security
![image](https://github.com/user-attachments/assets/0110af53-2468-4510-a42e-65d63e82afd9)
![image](https://github.com/user-attachments/assets/ed6d502f-9b1a-49f7-ac9a-db89283b7f05)

Spring Security의 흐름 아키텍쳐를 그려보고 필요한 필터와 동작 순서를 학습했습니다.

### 스프링 시큐리티 필터 동작 순서
  1. HTTP 요청 수신
     + 사용자가 브라우저를 통해 /login 또는 /register와 같은 엔드포인트에 요청을 보냅니다.
       
  2. 시큐리티 필터 체인(Security Filter Chain) 시작
     + 스프링 시큐리티는 요청을 **필터 체인(Filter chain)** 을 통해 처리합니다.
       이 체인은 여러 필터로 구성되어 있으며, 각 필터는 특정 보안 기능을 담당합니다.
  
  3. CORS 및 CSRF 필터
     + CORS 필터(CorsFilter) : 다른 출처로부터 오는 요청을 허용할지 결정하는 역할을 가집니다.
     + CSRF 필터(CSRFFilter) : CSRF 공격을 방지합니다. 해당 프로젝트의 경우, CSRF는 비활성화되어있습니다.(쿠키를 이용한 인증 방식 X)
  
  4. JWT 인증 필터 (JwtAuthenticationFilter) 적용
     + 요청이 들어오면, 스프링 시큐리티는 JwtAuthenticationFilter 필터를 실행합니다. 해당 필터는 다음과 같은 역할을 가집니다.
       1) JWT 토큰 추출 : Authorization 헤더에서 JWT 토큰을 추출합니다.
       2) 토큰 유효성 검사 : 추출된 토큰을 JwtTokenProvider의 validateToken 메서드를 사용해 유효성을 검사합니다.
       3) 사용자 정보 로드 : 토큰이 유효하다면, 토큰에서 사용자명을 추출(getUsernanmeFromToken)하여 `UserDetailsService`를 통해 사용자 정보를 가져옵니다.
       4) Security Context 설정 : 인증된 사용자 정보를 사용하여 `SecurityContext`에 저장하여 이후 요청을 인증된 상태로 처리합니다.

  5. 예외 처리(JwtAuthenticationEntryPoint)
     + 만약 JWT 토큰이 유효하지 않거나 없을 경우, `JwtAuthenticationEntryPoint`가 작동하여 클라이언트에게 `401 Unauthorized` 에러를 반환합니다.

  6. 요청 처리
     + 필터 체인을 통과한 후, 요청은 적절한 컨트롤러`Controller`로 전달됩니다.
       + 예를 들어, `/login` 엔드포인트에 대한 요청은 `AuthController`의 `login`메서드로 전달됩니다.

  7. 응답 반환
     + 요청이 정상적으로 처리되면, 컨트롤러는 JWT 토큰(인증 성공 시)이나 기타 응답 데이터를 반환합니다.
