package fullstack.labelary.config.auth;

import fullstack.labelary.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        // 누구나 접근 가능한 설정
                        "/",
                        "/css/**",
                        "/images/**",
                        "/js/**",
                        "/h2-console/**",
                        "/login/**"
                ).permitAll()
                .antMatchers(
                        // "USER"인 경우만 접근 가능
                        "/api/v1/**",
                        "/admin/**"
                ).hasRole(Role.USER.name())
                // 나머지 URL 요청은 무조건 인증
                .anyRequest().authenticated()
            .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 후 리다이렉팅 될 URL
            .and()
                // OAuth2 로그인 관련 설정
                .oauth2Login()
                .userInfoEndpoint() // 로그인 성공 시, 사용자 정보 설정
                .userService(customOAuth2UserService); // 로그인 성공 후, 사용자를 다룰 서비스 등록
    }
}