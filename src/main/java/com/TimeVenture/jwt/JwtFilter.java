import com.TimeVenture.model.entity.member.service.AuthService;
import com.TimeVenture.repository.member.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter { // 모든 요청에 대해 토큰 유효성 검증을 진행

    private final AuthService authService;
    @Value("${jwt.secret}")
    private String secretKey;
    private final JwtUtil jwtUtil;
    private MemberRepository memberRepository;
    private RedisTemplate<String, String> redisTemplate;
    private static Long rfExpiredMs = 1000 * 60 * 60 * 24 * 14L; // 리프레쉬 토큰의 만료 시간(14일)

    public JwtFilter(AuthService authService, JwtUtil jwtUtil, String secretKey) {
        this.secretKey = secretKey;
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @Override // 이 주소로 오는 건 토큰 없어도 됨.
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.startsWith("/api/oauth/login") || path.startsWith("/login/") || path.startsWith("/api/oauth2/authorization/") ||
                path.startsWith("/api/login/oauth2/") || path.startsWith("/api/oauth/logout") || path.startsWith("/favicon.ico");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);


        // 토큰이 없거나 Bearer로 시작하지 않는 경우
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "토큰을 잘못 보냈습니다");
            return;
        }

        // 토큰 꺼내기(첫 번째가 토큰이다. bearer 제외)
        String token = authorization.split(" ")[1];

        // 토큰 만료됐는지 확인
        if (JwtUtil.isExpired(token, secretKey)) {

            Long userId = JwtUtil.getUserId(token, secretKey);

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("refreshToken".equals(cookie.getName())) {
                        String refreshTokenCookie = cookie.getValue();
                        String result = jwtUtil.checkRefreshToken(refreshTokenCookie, userId);
                        if (result.equals("리프레시 토큰 만료")) {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "리프레시 토큰 만료");
                            return;
                        } else if (result.equals("리프레시 토큰 불일치")) {
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "리프레시 토큰 불일치");
                            return;
                        }
                    }
                }
            }
            String accessJwt = jwtUtil.createAccessJwt(userId, secretKey);
            String refreshJwt = jwtUtil.createRefreshToken(userId, secretKey);

            Cookie cookie = new Cookie("refreshToken", refreshJwt);

            // expires in 7 days
            cookie.setMaxAge(14 * 24 * 60 * 60);

            // optional properties
            cookie.setSecure(false); // 이거 https 적용해서 서버로 올리면 true로 바꿔야한다. 지금은 로컬에서 테스트라서 false로 해놓음
            cookie.setHttpOnly(true); // http only로 설정해서 javascript로 접근 못하도록 막음
            cookie.setPath("/");

            // add cookie to response
            response.addCookie(cookie);

            String jsonResponse = "{\"accessToken\":\"" + accessJwt + "\"}";
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);

            return;
        }

        // userId 토큰에서 꺼냄.
        Long userId = JwtUtil.getUserId(token, secretKey);
        log.info("userId:{}", userId);

        // 권한 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority("USER")));
        // Detail을 넣어준다.
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}