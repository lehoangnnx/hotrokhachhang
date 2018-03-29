package bcc.springhibernate.service;

import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.repository.TaikhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private TaikhoanRepository taiKhoanRepository;
@Autowired
HttpServletRequest request;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Taikhoan taikhoan = taiKhoanRepository.findByUsernameAndTrangthai(HtmlUtils.htmlEscape(username), "active");
		if (taikhoan == null) {
            throw new UsernameNotFoundException("User not found");

        }


		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		Set<Quyen> quyen = taikhoan.getQuyens();
		for (Quyen q : quyen) {
			grantedAuthorities.add(new SimpleGrantedAuthority(q.getMaquyen()));
		}
        System.out.println(taikhoan.getNhanvien().getBophan().getTenbophan());
        HttpSession session = request.getSession();
        session.setAttribute("taikhoan", taikhoan);
		return new org.springframework.security.core.userdetails.User(taikhoan.getUsername(), taikhoan.getMatkhau(), grantedAuthorities);
	}

}
