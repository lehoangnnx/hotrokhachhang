package bcc.springhibernate.model;
// Generated Jan 10, 2018 6:48:04 PM by Hibernate Tools 5.1.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Taikhoan generated by hbm2java
 */
@Entity
@Table(name = "taikhoan", catalog = "hotrobanhang", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Taikhoan implements java.io.Serializable {

	private Integer id;
	private Nhanvien nhanvien;
	private Quyen quyen;
	private String username;
	private String matkhau;
	private String email;
	private String thongtinkhac;
	private String trangthai;
	private Set<Quyen> quyens = new HashSet<Quyen>(0);
	private Set<Quyen> quyens_1 = new HashSet<Quyen>(0);

	public Taikhoan() {
	}

	public Taikhoan(Nhanvien nhanvien, Quyen quyen, String username, String matkhau) {
		this.nhanvien = nhanvien;
		this.quyen = quyen;
		this.username = username;
		this.matkhau = matkhau;
	}

	public Taikhoan(Nhanvien nhanvien, Quyen quyen, String username, String matkhau, String email, String thongtinkhac,
			String trangthai, Set<Quyen> quyens, Set<Quyen> quyens_1) {
		this.nhanvien = nhanvien;
		this.quyen = quyen;
		this.username = username;
		this.matkhau = matkhau;
		this.email = email;
		this.thongtinkhac = thongtinkhac;
		this.trangthai = trangthai;
		this.quyens = quyens;
		this.quyens_1 = quyens_1;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nhanvien_id", nullable = false)
	public Nhanvien getNhanvien() {
		return this.nhanvien;
	}

	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quyen_id", nullable = false)
	public Quyen getQuyen() {
		return this.quyen;
	}

	public void setQuyen(Quyen quyen) {
		this.quyen = quyen;
	}

	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "matkhau", nullable = false, length = 505)
	public String getMatkhau() {
		return this.matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	@Column(name = "email", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "thongtinkhac", length = 45)
	public String getThongtinkhac() {
		return this.thongtinkhac;
	}

	public void setThongtinkhac(String thongtinkhac) {
		this.thongtinkhac = thongtinkhac;
	}

	@Column(name = "trangthai", length = 45)
	public String getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "taikhoan_quyen", catalog = "hotrobanhang", joinColumns = {
			@JoinColumn(name = "taikhoan_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "quyen_id", nullable = false, updatable = false) })
	public Set<Quyen> getQuyens() {
		return this.quyens;
	}

	public void setQuyens(Set<Quyen> quyens) {
		this.quyens = quyens;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "taikhoan_quyen", catalog = "hotrobanhang", joinColumns = {
			@JoinColumn(name = "taikhoan_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "quyen_id", nullable = false, updatable = false) })
	public Set<Quyen> getQuyens_1() {
		return this.quyens_1;
	}

	public void setQuyens_1(Set<Quyen> quyens_1) {
		this.quyens_1 = quyens_1;
	}

}
