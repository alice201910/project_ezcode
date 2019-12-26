package org.ezcode.demo.security;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ezcode.demo.domain.MemberVO;
import org.ezcode.demo.mapper.MemberMapper;
import org.ezcode.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * CustomOAuth2UserService
 * 소셜 로그인 시 principal 객체 커스텀 반환
 * member 객체 추가
 * 
//  */
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService {

    // private HttpSession httpSession;
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행중인 서비스 구분하는 코드
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.info("" + registrationId);

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes 
        = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        log.info("attributes--------------" + attributes.getEmail());
        MemberVO member = memberMapper.read(attributes.getEmail());
        
        // vo가 null이면 member insert
        // if (member == null) {
        //     member = new MemberVO();
        //     member.setUserid(attributes.getEmail());
        //     member.setUserpw("");
        //     member.setUsername(attributes.getName());
        //     member.setEmail(attributes.getEmail());
        //     member.setTel("");
        //     member.setMlang("");
        //     memberService.join(member);
        // }
        
        // DefaultOAuth2User(java.util.Collection<? extends GrantedAuthority> authorities,
        // java.util.Map<java.lang.String,java.lang.Object> attributes, java.lang.String nameAttributeKey)
        return new CustomOAuth2User(
            Collections.singleton(new SimpleGrantedAuthority("ROLE_MEMBER")),
            attributes.getAttributes(),
            attributes.getNameAttributeKey(),
            member
        );

    }

    
}