/*
* Người Tạo : Nguyễn Lê Hoàng
* Ngày Tạo : 22/10/2017
* Lớp AjaxAuthenticationFailureHandler kế thừa Interface AuthenticationFailureHandler xử lý quá trình đăng nhập
* khi đăng nhập lỗi bằng Ajax
* */
package bcc.springhibernate.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component("ajaxAuthenticationFailureHandler")
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
			throws IOException, ServletException {
		
		// After login success
		response.getWriter().print(authEx.getMessage());
		response.getWriter().flush();
		
	}

}
