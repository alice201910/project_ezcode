package org.ezcode.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;


/**
 * OAuthLoginSuccessHandler
 */
@Slf4j
public class OAuthLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // OAuthAttributes attributes 
        // = OAuthAttributes.of("google", userNameAttributeName, oAuth2User.getAttributes());
        OAuth2AuthenticationToken token;
        
        CustomOAuth2User cuser = (CustomOAuth2User)authentication.getPrincipal();

        // log.info("" + cuser.getMember());
        // Class clz = authentication.getPrincipal().getClass();
        // log.info("" + cuser.getAttribute("name"));
        // log.info("" + authentication.getDetails());
        // log.info("" + authentication.getName());
        // log.info("" + authentication.getAuthorities());
        // log.info("" + authentication.getCredentials());
        
        // 전에 가입한 이력 있는지 확인..
        log.info("" + cuser.getMember());
        if (cuser.getMember() == null) {
            response.sendRedirect("/join");
        } else {

            response.sendRedirect("/");
        }



    }

    
}