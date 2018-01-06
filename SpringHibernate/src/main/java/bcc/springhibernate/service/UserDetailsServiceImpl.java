package bcc.springhibernate.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.repository.TaikhoanRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private TaikhoanRepository taiKhoanRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("UserName la gì" + username);
		Taikhoan taikhoan = taiKhoanRepository.findByUsername(HtmlUtils.htmlEscape(username));
		System.out.println("123" + taikhoan);
		if (taikhoan == null) {
			
			System.out.println("-------------------------------------------------------------------");
            throw new UsernameNotFoundException("User not found");
            
        }

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Quyen q = taikhoan.getQuyen();
		grantedAuthorities.add(new SimpleGrantedAuthority(q.getMaquyen()));
		return new org.springframework.security.core.userdetails.User(taikhoan.getUsername(), taikhoan.getMatkhau(), grantedAuthorities);
	}

}
