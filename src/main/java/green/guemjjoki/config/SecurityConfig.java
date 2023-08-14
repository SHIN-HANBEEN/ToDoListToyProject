package green.guemjjoki.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity /* Security 활성화 */


public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /*
    *  WebSecuriy는 HttpSecuriy보다 우선적으로 고려하기때문에
    *  접근제한에 따라 나눠서 작성할 수 있음.
    * */
    @Bean // 시큐리티 적용하지 않을 리소스 설정
    public WebSecurityCustomizer configure(){
        return web ->
                web.ignoring().requestMatchers(PathRequest.toH2Console());
//                        .requestMatchers("/static/**");
    }


    /* 시큐리티 5.7 이상부터  WebSecurityConfigurerAdapter 상속받는 대신 FilterChain 권장 (Deprecate됨.)
     * Spring Security의 더욱 유연하고 모듈화된 구조를 지원하기 위해서
     *  만일하나 WebSecurityConfigurerAdapter과 SecurityFilterChain를 같이 사용하는 것은 피해야한다.(서로 호환이 되지 않기 때문에)
     *  tmi - 원래 주로 사용하던 antMatcher메소드도  제거됨(requestMathcer로 대체)
     *  */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers(new AntPathRequestMatcher("/public/**")) // 일반 URL 패턴에 대해 접근 허용
//                        .requestMatchers("/register", "/login", "/api/**").permitAll() // 누구나 접근 가능
//                        .requestMatchers("/member/**").hasRole("ROLE_ADMIN")
//                        .anyRequest().authenticated()  // 설정 이외의 경로는 인증객체만 접근
                )  // and()메서드가 안되서 람다식으로 메뉴별로 리팩토링
                .formLogin(formLogin -> formLogin// 로그인 폼 설정
                        .loginProcessingUrl("/login") // 로그인 처리 url
                        .defaultSuccessUrl("/main") // 로그인 성공 시 이동할 페이지 경로
                        .permitAll()
                )
                // 로그아웃
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")  // 로그아웃 후 이동페이지
                        .invalidateHttpSession(true)  // 로그아웃 이후 세션 전체 삭제여부
                )
                .csrf(csrf->csrf.disable())
                //csrf(AbstractHttpConfigurer::disable)도 가능하나 메서드체인 방식과 함수호출 방식의 차이
                .build();
    }

}
