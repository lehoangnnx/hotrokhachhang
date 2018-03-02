package bcc.springhibernate.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.social.connect.ConnectionFactoryLocator;
//import org.springframework.social.connect.UsersConnectionRepository;
//import org.springframework.social.connect.web.ProviderSignInController;
//
//import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import bcc.springhibernate.config.*;
import bcc.springhibernate.service.UserDetailsServiceImpl;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

//Xác định lớp WebSecurityConfig là một lớp dùng để cấu hình
@Configuration
//Đẽ kích hoạt việc tích hợp Spring Security với Spring MVC
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	/*
     * Object principal =
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal(); String
	 * username= ""; if (principal instanceof UserDetails) { username =
	 * ((UserDetails) principal).getUsername(); } else { username =
	 * principal.toString(); }
	 */


    // Inject AjaxAuthenticationFailureHandler để cấu hình
    @Autowired
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    // Inject AjaxAuthenticationSuccessHandler để cấu hình
    @Autowired
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    // Inject ConnectionFactoryLocator để cấu hình
//    @Autowired
//    private ConnectionFactoryLocator connectionFactoryLocator;
//
//    // Inject UsersConnectionRepository để cấu hình
//    @Autowired
//    private UsersConnectionRepository usersConnectionRepository;

    // Inject AccessDeniedHandlerImpl để cấu hình
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

//    // Inject UserDetailsService để cấu hình
    @Autowired
    //private UserDetailsService userDetailsService;
    private UserDetailsService userDetailsService;
    // Mã hóa mật khẩu bằng Bcrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Autowired
    CustomLogoutSuccessHandler customLogoutSuccessHandler;
    // Cấu hình và xác thực, quyền truy cập của người dùng
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http
        .authorizeRequests()
            .antMatchers("/admin/**").hasAnyRole("ADMIN","CHAMSOC","BANHANG","GIAOHANG","TAICHINH")
            .antMatchers("/login").permitAll()
            .and()
        .formLogin()
            .loginPage("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/admin")
            .failureUrl("/login?error")
            .and()
        .exceptionHandling()
            .accessDeniedPage("/authlogin");


    }

}
