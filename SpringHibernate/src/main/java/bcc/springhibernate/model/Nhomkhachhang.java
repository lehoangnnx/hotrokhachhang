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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Nhomkhachhang generated by hbm2java
 */
@Entity
@Table(name = "nhomkhachhang", catalog = "hotrobanhang")
public class Nhomkhachhang implements java.io.Serializable {

	private Integer id;
	private String tennhom;
	private Integer diem;
	private Double phantram;
	private String trangthai;
	private String mota;
	private Set<Khachhang> khachhangs = new HashSet<Khachhang>(0);
	private Set<Khachhang> khachhangs_1 = new HashSet<Khachhang>(0);

	public Nhomkhachhang() {
	}

	public Nhomkhachhang(String tennhom, Integer diem, Double phantram, String trangthai, String mota,
			Set<Khachhang> khachhangs, Set<Khachhang> khachhangs_1) {
		this.tennhom = tennhom;
		this.diem = diem;
		this.phantram = phantram;
		this.trangthai = trangthai;
		this.mota = mota;
		this.khachhangs = khachhangs;
		this.khachhangs_1 = khachhangs_1;
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

	@Column(name = "tennhom", length = 45)
	public String getTennhom() {
		return this.tennhom;
	}

	public void setTennhom(String tennhom) {
		this.tennhom = tennhom;
	}

	@Column(name = "diem")
	public Integer getDiem() {
		return this.diem;
	}

	public void setDiem(Integer diem) {
		this.diem = diem;
	}

	@Column(name = "phantram", precision = 22, scale = 0)
	public Double getPhantram() {
		return this.phantram;
	}

	public void setPhantram(Double phantram) {
		this.phantram = phantram;
	}

	@Column(name = "trangthai", length = 45)
	public String getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	@Column(name = "mota", length = 95)
	public String getMota() {
		return this.mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nhomkhachhang")
	public Set<Khachhang> getKhachhangs() {
		return this.khachhangs;
	}

	public void setKhachhangs(Set<Khachhang> khachhangs) {
		this.khachhangs = khachhangs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nhomkhachhang")
	public Set<Khachhang> getKhachhangs_1() {
		return this.khachhangs_1;
	}

	public void setKhachhangs_1(Set<Khachhang> khachhangs_1) {
		this.khachhangs_1 = khachhangs_1;
	}

}
