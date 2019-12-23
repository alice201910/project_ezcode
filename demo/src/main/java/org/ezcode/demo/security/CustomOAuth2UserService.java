package org.ezcode.demo.security;

import java.util.Collections;

import org.ezcode.demo.domain.MemberVO;
import org.ezcode.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.extern.slf4j.Slf4j;

/**
 * CustomOAuth2UserService
//  */
// @Service
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService {

    // private final HttpSession httpSession;
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행중인 서비스 구분하는 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.info("" + registrationId);

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        MemberVO vo = memberMapper.read(attributes.getName());

        // DefaultOAuth2User(java.util.Collection<? extends GrantedAuthority> authorities,
        // java.util.Map<java.lang.String,java.lang.Object> attributes, java.lang.String nameAttributeKey)
        return new DefaultOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority(userNameAttributeName)),
            attributes.getAttributes(),
            attributes.getNameAttributeKey()
        );
    }

    
}