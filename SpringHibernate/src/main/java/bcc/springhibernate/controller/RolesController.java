package bcc.springhibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;


import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.service.QuyenService;


@Controller
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class RolesController {
	@Autowired
	QuyenService quyenService;
	
	@GetMapping("/roles")
	// Hiển thị danh sách quyền
	public String getAllQuyen(Model model, @RequestParam(name = "status", defaultValue = "active") String trangthai) {

		// Lấy danh sách quyền thoe status và sắp xếp theo ID
		List<Quyen> lst = quyenService.findAllByTrangThaiOrderByIdDesc(trangthai);
		model.addAttribute("lstquyen",lst);
		return "rolesmng";
	}
	
//	@GetMapping("/addroles")
//	// Hiển thị trang thêm quyền
//	public String addcategorys(Model model ) {
//
//		return "rolesmng";
//	}
	
	@PostMapping(value = "/roles")
	// Thêm quyền
	public String addQuyen(Model model,
			@RequestParam("idquyen") String id,
			@RequestParam("maquyen") String maquyen,
		   @RequestParam("tenquyen") String tenquyen, 
		   @RequestParam("mota") String mota, 
		   @RequestParam("trangthai") String trangthai,
		   RedirectAttributes redirectAttributes) {

		String sc = "Thêm quyền thành công!";
		String fa = "Thêm quyền thất bại!";
		try {
			
			Quyen q = new Quyen();
			if(!id.equals("")){
				
				q = quyenService.findById(Integer.parseInt(id));
				sc = "Cập nhật quyền thành công!";
				fa = "Cập nhật quyền thất bại!";
			}
			
			if(!maquyen.trim().equals("")) {
				q.setMaquyen(maquyen.trim());
			}
			if (!tenquyen.equals("")) {
				q.setTenquyen(tenquyen.trim());
			}
			if (!mota.equals("")) {
				q.setMota(mota.trim());
			}	
			q.setTrangthai(trangthai);
			quyenService.saveOrUpdate(q);
			
			redirectAttributes.addFlashAttribute("msg", sc);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", fa);
			return "redirect:/admin/roles";
		}

		return "redirect:/admin/roles?status="+ trangthai;
	}
	
	@GetMapping("/roles/{id}")
	// Hiển thị trang sửa quyền
	public String updateQuyen(Model model, @PathVariable("id") String id) {

		if(id.equals("add")){
			model.addAttribute("isshowlist","0");
			return "rolesmng";
		}
			
		// Lấy quyền theo Id
		Integer a = new Integer(id);
		Quyen q = quyenService.findById(a);
		List<Quyen> lst = quyenService.findAll();
		model.addAttribute("lstquyen",lst);
		model.addAttribute("isshowlist","0");
		model.addAttribute("quyen",q);
		return "rolesmng";
	}
	
	@PatchMapping("/roles")
	// Sửa quyền
	public String UpdateQuyen(Model model, 
			@RequestParam("maquyen") String maquyen,
			@RequestParam("id") Integer id,
			@RequestParam("tenquyen") String tenquyen, 
			@RequestParam("trangthai") String trangthai,
			@RequestParam("mota") String mota, 
			RedirectAttributes redirectAttributes) {

		try {
			Quyen q = quyenService.findById(id);
			if(!maquyen.trim().equals("")) {
				q.setMaquyen(maquyen.trim());
			}
			if (!tenquyen.trim().equals("")) {
				q.setTenquyen(tenquyen.trim());
			}
			if (!mota.trim().equals("")) {
				q.setMota(mota.trim());
			}

			q.setTrangthai(trangthai);
			quyenService.saveOrUpdate(q);
			
			redirectAttributes.addFlashAttribute("msg", "Sửa Quyền Thành Công");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msg", "Sửa Quyền Thất Bại");
			return "redirect:/admin/roles?status=" + trangthai;
		}
		
		return "redirect:/admin/roles?status=" + trangthai;
	}
	
	
	@DeleteMapping("/roles")
	// Xóa quyền
	public String deleteAllRoles(@RequestParam("arrayId") List<Integer> arrayId ,
								RedirectAttributes redirectAttributes) {


		try {
			arrayId.forEach(x -> {

				Quyen quyen= quyenService.findById(x);
				quyen.setTrangthai("deleted");
				quyenService.saveOrUpdate(quyen);
			});


			redirectAttributes.addFlashAttribute("msg", "Xóa Quyền Thành Công");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("msg", "Xóa Quyền Thất Bại");
		}

		return "redirect:/admin/roles?status=active";
	}
	
	

	
}
