package bcc.springhibernate.model;
// Generated Jan 20, 2018 8:57:07 AM by Hibernate Tools 5.1.5.Final

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
 * Kpi generated by hbm2java
 */
@Entity
@Table(name = "kpi", catalog = "hotrobanhang")
public class Kpi implements java.io.Serializable {


	private Integer id;
	private String ten;
	private String kieukpi;
	private String trangthai;
	private String mota;
	private Set<Nhanvienkpi> nhanvienkpis = new HashSet<Nhanvienkpi>(0);
	private Set<Nhanvienkpi> nhanvienkpis_1 = new HashSet<Nhanvienkpi>(0);

	public Kpi() {
	}

	public Kpi(String ten, String kieukpi, String trangthai, String mota, Set<Nhanvienkpi> nhanvienkpis, Set<Nhanvienkpi> nhanvienkpis_1) {
		this.ten = ten;
		this.kieukpi = kieukpi;
		this.trangthai = trangthai;
		this.mota = mota;
		this.nhanvienkpis = nhanvienkpis;
		this.nhanvienkpis_1 = nhanvienkpis_1;
	}

	@Id @GeneratedValue(strategy=IDENTITY)


	@Column(name="id", unique=true, nullable=false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="ten", length=45)
	public String getTen() {
		return this.ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}


	@Column(name="kieukpi", length=50)
	public String getKieukpi() {
		return this.kieukpi;
	}

	public void setKieukpi(String kieukpi) {
		this.kieukpi = kieukpi;
	}


	@Column(name="trangthai", length=65535)
	public String getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}


	@Column(name="mota")
	public String getMota() {
		return this.mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="kpi")
	public Set<Nhanvienkpi> getNhanvienkpis() {
		return this.nhanvienkpis;
	}

	public void setNhanvienkpis(Set<Nhanvienkpi> nhanvienkpis) {
		this.nhanvienkpis = nhanvienkpis;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="kpi")
	public Set<Nhanvienkpi> getNhanvienkpis_1() {
		return this.nhanvienkpis_1;
	}

	public void setNhanvienkpis_1(Set<Nhanvienkpi> nhanvienkpis_1) {
		this.nhanvienkpis_1 = nhanvienkpis_1;
	}


}
