package bcc.springhibernate.model;
// Generated Jan 13, 2018 1:49:35 PM by Hibernate Tools 5.1.5.Final

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
 * Nhanvienkpi generated by hbm2java
 */
@Entity
@Table(name = "nhanvienkpi", catalog = "hotrobanhang")
public class Nhanvienkpi implements java.io.Serializable {

	private Integer id;
	private Kpi kpi;
	private Nhanvien nhanvien;
	private Integer so;
	private Date ngaydangky;
	private Date ngayhoanthanh;
	private String trangthai;
	private Double mucdohoanthanh;
	private String mota;

	public Nhanvienkpi() {
	}

	public Nhanvienkpi(Kpi kpi, Nhanvien nhanvien, Integer so, Date ngaydangky, Date ngayhoanthanh, String trangthai,
			Double mucdohoanthanh, String mota) {
		this.kpi = kpi;
		this.nhanvien = nhanvien;
		this.so = so;
		this.ngaydangky = ngaydangky;
		this.ngayhoanthanh = ngayhoanthanh;
		this.trangthai = trangthai;
		this.mucdohoanthanh = mucdohoanthanh;
		this.mota = mota;
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
	@JoinColumn(name = "kpi")
	public Kpi getKpi() {
		return this.kpi;
	}

	public void setKpi(Kpi kpi) {
		this.kpi = kpi;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nhanvien")
	public Nhanvien getNhanvien() {
		return this.nhanvien;
	}

	public void setNhanvien(Nhanvien nhanvien) {
		this.nhanvien = nhanvien;
	}

	@Column(name = "so")
	public Integer getSo() {
		return this.so;
	}

	public void setSo(Integer so) {
		this.so = so;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngaydangky", length = 19)
	public Date getNgaydangky() {
		return this.ngaydangky;
	}

	public void setNgaydangky(Date ngaydangky) {
		this.ngaydangky = ngaydangky;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngayhoanthanh", length = 19)
	public Date getNgayhoanthanh() {
		return this.ngayhoanthanh;
	}

	public void setNgayhoanthanh(Date ngayhoanthanh) {
		this.ngayhoanthanh = ngayhoanthanh;
	}

	@Column(name = "trangthai", length = 45)
	public String getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	@Column(name = "mucdohoanthanh", precision = 22, scale = 0)
	public Double getMucdohoanthanh() {
		return this.mucdohoanthanh;
	}

	public void setMucdohoanthanh(Double mucdohoanthanh) {
		this.mucdohoanthanh = mucdohoanthanh;
	}

	@Column(name = "mota")
	public String getMota() {
		return this.mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

}
