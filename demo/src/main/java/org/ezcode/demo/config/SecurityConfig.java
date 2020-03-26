package org.ezcode.demo.config;

import javax.sql.DataSource;

import org.ezcode.demo.mapper.MemberMapper;
import org.ezcode.demo.security.CustomOAuth2UserService;
import org.ezcode.demo.security.CustomUserDetailsService;
import org.ezcode.demo.security.OAuthLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * SecurityConfig
 * 
 * @EnableWebSecurity - 스프링 시큐리티 설정 활성화
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Setter(onMethod_ = {@Autowired})
    public DataSource dataSource;

    @Autowired
    private MemberMapper memberMapper;

    // @Setter(onMethod_ = {@Autowired})
    // private CustomOAuth2UserService customOAuth2UserServiceq;

    

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserService())
        .passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsService customUserService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public CustomOAuth2UserService customOAuth2UserService() {
        return new CustomOAuth2UserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
        .loginPage("/oauth_login")
        .loginProcessingUrl("/login");

        http.logout()
        .logoutUrl("/customLogout")
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .deleteCookies("remember-me", "JESSION_ID");

        http.rememberMe()
	      .key("ezcode")
	      .tokenRepository(persistentTokenRepository())
          .tokenValiditySeconds(604800);
        
        http.authorizeRequests()    // URL별 권한 관리 설정하는 옵션 시작점
        .antMatchers("/oauth_login", "/",
         "/css/**", "/images/**", "/js/**", "/font/**", "/fonts/**", "/scss/**", "/idCheck",
          "/cshop/**", "/search/**", "/join", "/review/**", "/profile", "/notice/**", "/viewFile")
        .permitAll()
        .antMatchers("/member/admin").access("hasRole('ROLE_ADMIN')")
        .antMatchers("/member/member").access("hasRole('ROLE_MEMBER')") 
        .anyRequest()  // 설정된 값들 이외의 나머지 url들
        .authenticated()    // 인증된 사용자 ( = 로그인된 사용자)
        .and()
        .oauth2Login()
        .loginPage("/oauth_login")
        .successHandler(oAuthLoginSuccessHandler())
        .authorizationEndpoint()
        .baseUri("/oauth2/authorize-client")
        .authorizationRequestRepository(authorizationRequestRepository())
        .and()
        .tokenEndpoint()
        .accessTokenResponseClient(accessTokenResponseClient())
        .and()
        // .defaultSuccessUrl("/")
        .failureUrl("/loginFailure")
        .userInfoEndpoint().userService(customOAuth2UserService());

        // .userInfoEndPoint() OAuth2 로그인 성공 이후 사용자 정보를 가져 올때의 설정들 담당
        // .userService(customOAuth2UserService) - 소셜 로그인 성공 시 후속 조치를 진행할 USerService 인터페이스의 구현체를 등록
        // 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능명시 가능.
    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public OAuthLoginSuccessHandler oAuthLoginSuccessHandler() {
        return new OAuthLoginSuccessHandler();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        DefaultAuthorizationCodeTokenResponseClient accessTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        return accessTokenResponseClient;
    }

    

    @Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public PrincipalExtractor principalExtractor() {
    //     return map -> {
    //         String principalId = (String) map.get("id");
    //         MemberVO vo = memberMapper.read(principalId);
    //         if (vo == null) {
    //             log.info("No user found, generating profile for {}", principalId);
    //             vo = new MemberVO();

    //             vo.setUserid((String) map.get("name"));
    //             vo.setUsername((String) map.get("name"));
    //             vo.setEmail((String) map.get("name"));
                

    //         } 
            
    //         return vo;
    //     };
    // }
    
}