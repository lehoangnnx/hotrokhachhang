package bcc.springhibernate.model;
// Generated Jan 10, 2018 6:48:04 PM by Hibernate Tools 5.1.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Khachhang generated by hbm2java
 */
@Entity
@Table(name = "khachhang", catalog = "hotrobanhang", uniqueConstraints = @UniqueConstraint(columnNames = "makh"))
public class Khachhang implements java.io.Serializable {

	private Integer id;
	private Loaikhachhang loaikhachhang;
	private Nhomkhachhang nhomkhachhang;
	private String makh;
	private String ten;
	private Integer manganhnghe;
	private String sotaikhoan;
	private String diachi;
	private String sodienthoai;
	private String tendaidien;
	private String dienthoaidaidien;
	private Date ngaysinhnhatndd;
	private String tenphutrach;
	private String dienthoaiphutrach;
	private Date ngaysinhphutrach;
	private String sogpkd;
	private Date ngaycap;
	private String noicap;
	private String trangthai;
	private Long sotienchamsoc;
	private Long sotiendachamsoc;
	private Integer diem;
	private Integer solanchamsoc;
	private Integer solandamphan;
	private Boolean trangthainhac;
	private Integer diemtiemnang;
	private String ghichu;
	private Set<Chamsoc> chamsocs = new HashSet<Chamsoc>(0);
	private Set<Chamsoc> chamsocs_1 = new HashSet<Chamsoc>(0);
	private Set<Hoadon> hoadons = new HashSet<Hoadon>(0);
	private Set<Hoadon> hoadons_1 = new HashSet<Hoadon>(0);

	public Khachhang() {
	}

	public Khachhang(Nhomkhachhang nhomkhachhang, String makh, String ten) {
		this.nhomkhachhang = nhomkhachhang;
		this.makh = makh;
		this.ten = ten;
	}

	public Khachhang(Loaikhachhang loaikhachhang, Nhomkhachhang nhomkhachhang, String makh, String ten,
			Integer manganhnghe, String sotaikhoan, String diachi, String sodienthoai, String tendaidien,
			String dienthoaidaidien, Date ngaysinhnhatndd, String tenphutrach, String dienthoaiphutrach,
			Date ngaysinhphutrach, String sogpkd, Date ngaycap, String noicap, String trangthai, Long sotienchamsoc,
			Long sotiendachamsoc, Integer diem, Integer solanchamsoc, Integer solandamphan, Boolean trangthainhac,
			Integer diemtiemnang, String ghichu, Set<Chamsoc> chamsocs, Set<Chamsoc> chamsocs_1, Set<Hoadon> hoadons,
			Set<Hoadon> hoadons_1) {
		this.loaikhachhang = loaikhachhang;
		this.nhomkhachhang = nhomkhachhang;
		this.makh = makh;
		this.ten = ten;
		this.manganhnghe = manganhnghe;
		this.sotaikhoan = sotaikhoan;
		this.diachi = diachi;
		this.sodienthoai = sodienthoai;
		this.tendaidien = tendaidien;
		this.dienthoaidaidien = dienthoaidaidien;
		this.ngaysinhnhatndd = ngaysinhnhatndd;
		this.tenphutrach = tenphutrach;
		this.dienthoaiphutrach = dienthoaiphutrach;
		this.ngaysinhphutrach = ngaysinhphutrach;
		this.sogpkd = sogpkd;
		this.ngaycap = ngaycap;
		this.noicap = noicap;
		this.trangthai = trangthai;
		this.sotienchamsoc = sotienchamsoc;
		this.sotiendachamsoc = sotiendachamsoc;
		this.diem = diem;
		this.solanchamsoc = solanchamsoc;
		this.solandamphan = solandamphan;
		this.trangthainhac = trangthainhac;
		this.diemtiemnang = diemtiemnang;
		this.ghichu = ghichu;
		this.chamsocs = chamsocs;
		this.chamsocs_1 = chamsocs_1;
		this.hoadons = hoadons;
		this.hoadons_1 = hoadons_1;
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
	@JoinColumn(name = "maloaikhachhang")
	public Loaikhachhang getLoaikhachhang() {
		return this.loaikhachhang;
	}

	public void setLoaikhachhang(Loaikhachhang loaikhachhang) {
		this.loaikhachhang = loaikhachhang;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nhomkhachhang_id", nullable = false)
	public Nhomkhachhang getNhomkhachhang() {
		return this.nhomkhachhang;
	}

	public void setNhomkhachhang(Nhomkhachhang nhomkhachhang) {
		this.nhomkhachhang = nhomkhachhang;
	}

	@Column(name = "makh", unique = true, nullable = false, length = 45)
	public String getMakh() {
		return this.makh;
	}

	public void setMakh(String makh) {
		this.makh = makh;
	}

	@Column(name = "ten", nullable = false)
	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	@Column(name = "manganhnghe")
	public Integer getManganhnghe() {
		return this.manganhnghe;
	}

	public void setManganhnghe(Integer manganhnghe) {
		this.manganhnghe = manganhnghe;
	}

	@Column(name = "sotaikhoan", length = 20)
	public String getSotaikhoan() {
		return this.sotaikhoan;
	}

	public void setSotaikhoan(String sotaikhoan) {
		this.sotaikhoan = sotaikhoan;
	}

	@Column(name = "diachi")
	public String getDiachi() {
		return this.diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	@Column(name = "sodienthoai", length = 15)
	public String getSodienthoai() {
		return this.sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

	@Column(name = "tendaidien", length = 45)
	public String getTendaidien() {
		return this.tendaidien;
	}

	public void setTendaidien(String tendaidien) {
		this.tendaidien = tendaidien;
	}

	@Column(name = "dienthoaidaidien", length = 15)
	public String getDienthoaidaidien() {
		return this.dienthoaidaidien;
	}

	public void setDienthoaidaidien(String dienthoaidaidien) {
		this.dienthoaidaidien = dienthoaidaidien;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngaysinhnhatndd", length = 19)
	public Date getNgaysinhnhatndd() {
		return this.ngaysinhnhatndd;
	}

	public void setNgaysinhnhatndd(Date ngaysinhnhatndd) {
		this.ngaysinhnhatndd = ngaysinhnhatndd;
	}

	@Column(name = "tenphutrach", length = 45)
	public String getTenphutrach() {
		return this.tenphutrach;
	}

	public void setTenphutrach(String tenphutrach) {
		this.tenphutrach = tenphutrach;
	}

	@Column(name = "dienthoaiphutrach", length = 15)
	public String getDienthoaiphutrach() {
		return this.dienthoaiphutrach;
	}

	public void setDienthoaiphutrach(String dienthoaiphutrach) {
		this.dienthoaiphutrach = dienthoaiphutrach;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngaysinhphutrach", length = 19)
	public Date getNgaysinhphutrach() {
		return this.ngaysinhphutrach;
	}

	public void setNgaysinhphutrach(Date ngaysinhphutrach) {
		this.ngaysinhphutrach = ngaysinhphutrach;
	}

	@Column(name = "sogpkd", length = 50)
	public String getSogpkd() {
		return this.sogpkd;
	}

	public void setSogpkd(String sogpkd) {
		this.sogpkd = sogpkd;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngaycap", length = 19)
	public Date getNgaycap() {
		return this.ngaycap;
	}

	public void setNgaycap(Date ngaycap) {
		this.ngaycap = ngaycap;
	}

	@Column(name = "noicap", length = 45)
	public String getNoicap() {
		return this.noicap;
	}

	public void setNoicap(String noicap) {
		this.noicap = noicap;
	}

	@Column(name = "trangthai", length = 45)
	public String getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	@Column(name = "sotienchamsoc", precision = 10, scale = 0)
	public Long getSotienchamsoc() {
		return this.sotienchamsoc;
	}

	public void setSotienchamsoc(Long sotienchamsoc) {
		this.sotienchamsoc = sotienchamsoc;
	}

	@Column(name = "sotiendachamsoc", precision = 10, scale = 0)
	public Long getSotiendachamsoc() {
		return this.sotiendachamsoc;
	}

	public void setSotiendachamsoc(Long sotiendachamsoc) {
		this.sotiendachamsoc = sotiendachamsoc;
	}

	@Column(name = "diem")
	public Integer getDiem() {
		return this.diem;
	}

	public void setDiem(Integer diem) {
		this.diem = diem;
	}

	@Column(name = "solanchamsoc")
	public Integer getSolanchamsoc() {
		return this.solanchamsoc;
	}

	public void setSolanchamsoc(Integer solanchamsoc) {
		this.solanchamsoc = solanchamsoc;
	}

	@Column(name = "solandamphan")
	public Integer getSolandamphan() {
		return this.solandamphan;
	}

	public void setSolandamphan(Integer solandamphan) {
		this.solandamphan = solandamphan;
	}

	@Column(name = "trangthainhac")
	public Boolean getTrangthainhac() {
		return this.trangthainhac;
	}

	public void setTrangthainhac(Boolean trangthainhac) {
		this.trangthainhac = trangthainhac;
	}

	@Column(name = "diemtiemnang")
	public Integer getDiemtiemnang() {
		return this.diemtiemnang;
	}

	public void setDiemtiemnang(Integer diemtiemnang) {
		this.diemtiemnang = diemtiemnang;
	}

	@Column(name = "ghichu")
	public String getGhichu() {
		return this.ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khachhang")
	public Set<Chamsoc> getChamsocs() {
		return this.chamsocs;
	}

	public void setChamsocs(Set<Chamsoc> chamsocs) {
		this.chamsocs = chamsocs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khachhang")
	public Set<Chamsoc> getChamsocs_1() {
		return this.chamsocs_1;
	}

	public void setChamsocs_1(Set<Chamsoc> chamsocs_1) {
		this.chamsocs_1 = chamsocs_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khachhang")
	public Set<Hoadon> getHoadons() {
		return this.hoadons;
	}

	public void setHoadons(Set<Hoadon> hoadons) {
		this.hoadons = hoadons;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "khachhang")
	public Set<Hoadon> getHoadons_1() {
		return this.hoadons_1;
	}

	public void setHoadons_1(Set<Hoadon> hoadons_1) {
		this.hoadons_1 = hoadons_1;
	}

}
