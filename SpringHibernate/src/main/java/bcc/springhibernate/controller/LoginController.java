package bcc.springhibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("authlogin")
    String authLogin() {
        return "authlogin";
    }


}
