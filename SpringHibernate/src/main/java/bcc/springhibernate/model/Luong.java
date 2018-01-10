package bcc.springhibernate.model;
// Generated Jan 10, 2018 6:48:04 PM by Hibernate Tools 5.1.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Luong generated by hbm2java
 */
@Entity
@Table(name = "luong", catalog = "hotrobanhang")
public class Luong implements java.io.Serializable {

	private Integer id;
	private Nhanvien nhanvien;
	private Long luong;
	private Long thuong;
	private Double chietkhau;
	private Date thoigian;
	private String trangthai;
	private String ghichu;

	public Luong() {
	}

	public Luong(Nhanvien nhanvien, Long luong, Long thuong, Double chietkhau, Date thoigian, String trangthai,
			String ghichu) {
		this.nhanvien = nhanvien;
		this.luong = luong;
		this.thuong = thuong;
		this.chietkhau = chietkhau;
		this.thoigian = thoigian;
		this.trangthai = trangthai;
		this.ghichu = ghichu;
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
	@JoinColumn(name = "nhanvien_id")
	public Nhanvien getNhanvien() {
		return this.nhanvien;
	}

	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}

	@Column(name = "luong", precision = 10, scale = 0)
	public Long getLuong() {
		return this.luong;
	}

	public void setLuong(Long luong) {
		this.luong = luong;
	}

	@Column(name = "thuong", precision = 10, scale = 0)
	public Long getThuong() {
		return this.thuong;
	}

	public void setThuong(Long thuong) {
		this.thuong = thuong;
	}

	@Column(name = "chietkhau", precision = 22, scale = 0)
	public Double getChietkhau() {
		return this.chietkhau;
	}

	public void setChietkhau(Double chietkhau) {
		this.chietkhau = chietkhau;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "thoigian", length = 19)
	public Date getThoigian() {
		return this.thoigian;
	}

	public void setThoigian(Date thoigian) {
		this.thoigian = thoigian;
	}

	@Column(name = "trangthai", length = 45)
	public String getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	@Column(name = "ghichu")
	public String getGhichu() {
		return this.ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

}
