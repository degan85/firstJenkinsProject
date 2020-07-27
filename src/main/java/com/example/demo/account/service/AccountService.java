package com.example.demo.account.service;

import com.example.demo.account.dao.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService implements UserDetailsService {

	@Autowired
	private UserRepository accountRepository;
	
	@Transactional
	public String registation(com.example.demo.account.vo.User user) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        return accountRepository.registation(user).getEmail();
	}
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername ::: >>>> 진입");
		Optional<com.example.demo.account.vo.User> userEntityWrapper;
		com.example.demo.account.vo.User account = new com.example.demo.account.vo.User();
		List<GrantedAuthority> authorities = new ArrayList<>();
		try {
			userEntityWrapper = accountRepository.findById(userid);
			
			if(userEntityWrapper.isPresent()) {
				account = userEntityWrapper.get();
				if(!"original".equals(account.getType())) {
					throw new UsernameNotFoundException("등록되지 않은 회원이거나, 패스워드가 틀렸습니다.");
				}
			} else {
				throw new UsernameNotFoundException("등록되지 않은 회원이거나, 패스워드가 틀렸습니다.");
			}
			
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//		    if (("wato@wato.com").equals(userid)) {
//		        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		    }
//		    else if (("wato").equals(userid)) {
//		        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		    } else {
//		        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
//		    }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return new User(account.getEmail(), account.getPassword(), authorities);
	}
	
	@Transactional
	public com.example.demo.account.vo.User findById(String email) throws Exception {
		return accountRepository.findUserById(email);
	}
}
