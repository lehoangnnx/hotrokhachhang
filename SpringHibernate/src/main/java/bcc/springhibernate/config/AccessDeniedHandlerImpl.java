
/*
* Người Tạo : Nguyễn Lê Hoàng
* Ngày Tạo : 01/12/2017
* Lớp AccessDeniedHandlerImpl kế thừa AccessDeniedHandler thực thi khi quyền truy cập không cho phép.
* */
package bcc.springhibernate.config;



import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendRedirect("/405.html");
    }
}
