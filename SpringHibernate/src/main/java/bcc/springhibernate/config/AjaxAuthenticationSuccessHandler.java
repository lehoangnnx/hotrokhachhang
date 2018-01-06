/*
* Người Tạo : Nguyễn Lê Hoàng
* Ngày Tạo : 22/10/2017
* Lớp AjaxAuthenticationSuccessHandler kế thừa Interface AuthenticationSuccessHandler xử lý quá trình đăng nhập
* khi đăng nhập thành công bằng Ajax
* */
package bcc.springhibernate.config;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import bcc.springhibernate.model.Quyen;

@Component("ajaxAuthenticationSuccessHandler")
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		
		// Sau khi đăng nhập thành công
		String contentType= "text/html;charset=UTF-8";
	    response.setContentType(contentType);
	    response.setCharacterEncoding("utf-8");     

		response.getWriter().print("success");
		response.getWriter().flush();
		
	}
	
	
	
	/**
	 * This method extracts the roles of currently logged-in user and return
	 * appropriate URL according to user's role.
	 * @param authentication
	 * @return
	 */
	public String determineTargetUrl(Authentication authentication){
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for(GrantedAuthority authority: authorities){
			System.out.println("ROLE: "+ authority.getAuthority());
			roles.add(authority.getAuthority());
		}
		
		if(roles.contains("ROLE_ADMIN")){
			return "/admin";
		}else if(roles.contains("ROLE_USER")){
			return "/article";
		}else{
			return "/error/403"; // Access Denied
		}
	}

}
