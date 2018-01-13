package bcc.springhibernate.model;
// Generated Jan 13, 2018 1:49:35 PM by Hibernate Tools 5.1.5.Final

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
 * Bophan generated by hbm2java
 */
@Entity
@Table(name = "bophan", catalog = "hotrobanhang")
public class Bophan implements java.io.Serializable {

	private Integer id;
	private String tenbophan;
	private String mota;
	private String vitri;
	private String trangthai;
	private String ghichu;
	private Set<Nhanvien> nhanviens = new HashSet<Nhanvien>(0);
	private Set<Nhanvien> nhanviens_1 = new HashSet<Nhanvien>(0);

	public Bophan() {
	}

	public Bophan(String tenbophan, String mota, String vitri, String trangthai, String ghichu, Set<Nhanvien> nhanviens,
			Set<Nhanvien> nhanviens_1) {
		this.tenbophan = tenbophan;
		this.mota = mota;
		this.vitri = vitri;
		this.trangthai = trangthai;
		this.ghichu = ghichu;
		this.nhanviens = nhanviens;
		this.nhanviens_1 = nhanviens_1;
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

	@Column(name = "tenbophan", length = 45)
	public String getTenbophan() {
		return this.tenbophan;
	}

	public void setTenbophan(String tenbophan) {
		this.tenbophan = tenbophan;
	}

	@Column(name = "mota")
	public String getMota() {
		return this.mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	@Column(name = "vitri", length = 45)
	public String getVitri() {
		return this.vitri;
	}

	public void setVitri(String vitri) {
		this.vitri = vitri;
	}

	@Column(name = "trangthai", length = 45)
	public String getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	@Column(name = "ghichu", length = 95)
	public String getGhichu() {
		return this.ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bophan")
	public Set<Nhanvien> getNhanviens() {
		return this.nhanviens;
	}

	public void setNhanviens(Set<Nhanvien> nhanviens) {
		this.nhanviens = nhanviens;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bophan")
	public Set<Nhanvien> getNhanviens_1() {
		return this.nhanviens_1;
	}

	public void setNhanviens_1(Set<Nhanvien> nhanviens_1) {
		this.nhanviens_1 = nhanviens_1;
	}

}
