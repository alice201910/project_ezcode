package org.ezcode.demo.config;

import javax.sql.DataSource;

import org.ezcode.demo.security.CustomUserDetailsService;
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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * SecurityConfig
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Setter(onMethod_ = {@Autowired})
    public DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserService())
        .passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsService customUserService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/member/all").permitAll()
        .antMatchers("/member/admin").access("hasRole('ROLE_ADMIN')")
        .antMatchers("/member/member").access("hasRole('ROLE_MEMBER')");

        http.formLogin()
        .loginPage("/customLogin")
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
    }

    @Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}