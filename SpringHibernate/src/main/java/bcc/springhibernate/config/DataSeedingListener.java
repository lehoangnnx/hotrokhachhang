/*
* Người Tạo : Nguyễn Lê Hoàng
* Ngày Tạo : 22/10/2017
* Lớp DataSeedingListener kế thừa interface  ApplicationListener<ContextRefreshedEvent>
*     bắt sự kiện khi website bắt đầu chạy
* */
package bcc.springhibernate.config;

import java.util.Date;
import java.util.HashSet;

import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.model.Taikhoan;
import bcc.springhibernate.repository.NhanvienRepository;
import bcc.springhibernate.repository.QuyenRepository;
import bcc.springhibernate.repository.TaikhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private TaikhoanRepository taikhoanRepository;

	@Autowired
	private QuyenRepository quyenRepository;
	@Autowired
	private NhanvienRepository nhanvienRepository;

	@Autowired 
	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		/*// Roles
		if (rolesRepository.findByName("ROLE_ADMIN") == null) {
			rolesRepository.save(new Roles("ROLE_ADMIN", "active"));
		}
		
		if (rolesRepository.findByName("ROLE_MEMBER") == null) {
			rolesRepository.save(new Roles("ROLE_MEMBER", "active"));
		}*/
		
		// Admin account
		/*if (taikhoanRepository.findByUsername("admin1") == null) {
			Taikhoan taikhoan = new Taikhoan();
			taikhoan.setUsername("admin1");
			taikhoan.setEmail("admin1@gmail.com");
			taikhoan.setMatkhau(passwordEncoder.encode("123456"));
			taikhoan.setThongtinkhac("123");
			Quyen quyen = quyenRepository.findById(1);
			Nhanvien nhanvien =nhanvienRepository.findById(1);
			taikhoan.setQuyen(quyen);
			taikhoan.setNhanvien(nhanvien);
			taikhoanRepository.save(taikhoan);
		}
		*/
		/*// Member account
		if (usersRepository.findByUserName("nguoidung") == null) {
			Users user = new Users();
			user.setUserName("nguoidung");
			user.setEmail("nguoidung@gmail.com");
			user.setPasword(passwordEncoder.encode("123456"));
			user.setStatus("active");
			user.setCreatedDate(new Date());
			user.setLoggedInDate(new Date());
			user.setIsOnline((byte) 1);
			HashSet<Roles> roles = new HashSet<>();
			
			roles.add(rolesRepository.findByName("ROLE_MEMBER"));
			user.setRoleses(roles);
			usersRepository.save(user);
		}
		*/
		/*for (int i = 200; i < 300; i++) {
			Article article= new Article();
			article.setTitle("Title Article " +i);
			article.setSlug("title-article-"+i);
			article.setSubContent("Sub Content :" +i);
			article.setMainContent("Main ConTent :" +i);
			article.setAuthor("Author :" +i);
			article.setStatus("active");
			article.setViews(2);
			article.setCreatedDate(new Date());
			article.setShowDate(new Date());
			article.setUsers(usersRepository.findByUserId(3));
			HashSet<ArticleCategory> articleCategory = new HashSet<>();
			articleCategory.add(articleCategoryRepository.findByArticleCategoryId(1));
			article.setArticleCategories(articleCategory);
			
			articleRepository.save(article);

		}*/
	}

}
