package bcc.springhibernate.controller;

import bcc.springhibernate.service.TaikhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.Principal;
import java.text.ParseException;

@Controller
public class MainController {
    @Autowired
    TaikhoanService taikhoanService;
    @Autowired
    ThongBao thongBao;

    //@ModelAttribute("taikhoan")
    // Lưu Session Thông tin người dùng đăng nhập
	/*public void sessionUser(Principal principal, HttpSession session) {
		if (principal != null) {
			System.out.println(principal.getName());
			session.setAttribute("taikhoan", taikhoanService.findByUsername(principal.getName()));

		}
	}*/

    @RequestMapping("/")
    public String index(Principal principal, Model model) {

        if (principal != null) {

            return "redirect:/admin";
        }
        return "redirect:/login";
    }

    @RequestMapping("/admin")
    public String home(Model model, HttpServletRequest request, Principal principal) throws ParseException {
        thongBao.thongbao(model, request, principal);
        return "index";
    }

    @GetMapping(value = {"login"})
    public String pageLogin(Model model) {
       /* try {
            MakeBackup();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return "login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping(value = {"/403", "/*"})
    public String page403(Model model) {

        return "403";
    }
    public void MakeBackup() throws IOException {
        String dump = "C:\\xampp\\mysql\\bin\\mysqldump.exe "      //Path to mysql
                + "--host=localhost "       //Mysql hostname
                + "--port=3306 "            //Mysql portnumber
                + "--user=root "            //Mysql username
                + "--password= "        //Mysql password
                + "--add-drop-table "       //Add a DROP TABLE statement before each CREATE TABLE statement
                + "--add-drop-database "    //Add a DROP DATABASE statement before each CREATE DATABASE statement
                + "--complete-insert "      //Use complete INSERT statements that include column names.
                + "--extended-insert "      //Use multiple-row INSERT syntax that include several VALUES lists
                + "--default-character-set=utf8 "
                + "hotrobanhang ";                  //Mysql databasename


        Process run = Runtime.getRuntime().exec(dump);

        InputStream in = run.getInputStream();

        int nextChar;
        StringBuffer sb = new StringBuffer();

        while ((nextChar = in.read()) != -1) {
            sb.append((char) nextChar);
        }

        //Here, you can for example write it to a file and save it
        File f = new File("C:/Users/nhung/Downloads/a.txt");
        BufferedWriter bwr = new BufferedWriter(new FileWriter(f));

        //write contents of StringBuffer to a file
        bwr.write(sb.toString());

        //flush the stream
        bwr.flush();

        //close the stream
        bwr.close();
        System.out.println(sb);
    }
}
