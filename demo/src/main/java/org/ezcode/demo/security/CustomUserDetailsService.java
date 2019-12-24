package org.ezcode.demo.security;

import org.ezcode.demo.domain.MemberVO;
import org.ezcode.demo.mapper.MemberMapper;
import org.ezcode.demo.security.domain.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * CustomUserDetailsService
 */
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	// private HttpSession httpSession;

	@Setter(onMethod_ = { @Autowired })
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
		log.warn("Load User By UserName : " + userName);
		MemberVO vo = memberMapper.read(userName);

		log.warn("queried by member mapper: " + vo);
		
		// httpSession.setAttribute("member", vo);

		return vo == null ? null : new CustomUser(vo);
	}
}