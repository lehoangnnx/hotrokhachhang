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
import javax.persistence.UniqueConstraint;

/**
 * Nhomhang generated by hbm2java
 */
@Entity
@Table(name = "nhomhang", catalog = "hotrobanhang", uniqueConstraints = @UniqueConstraint(columnNames = "manhom"))
public class Nhomhang implements java.io.Serializable {

	private Integer id;
	private String manhom;
	private Integer manhomcha;
	private String tennhom;
	private String trangthai;
	private String mota;
	private Set<Hanghoa> hanghoas = new HashSet<Hanghoa>(0);
	private Set<Hanghoa> hanghoas_1 = new HashSet<Hanghoa>(0);

	public Nhomhang() {
	}

	public Nhomhang(String manhom, Integer manhomcha, String tennhom, String trangthai, String mota,
			Set<Hanghoa> hanghoas, Set<Hanghoa> hanghoas_1) {
		this.manhom = manhom;
		this.manhomcha = manhomcha;
		this.tennhom = tennhom;
		this.trangthai = trangthai;
		this.mota = mota;
		this.hanghoas = hanghoas;
		this.hanghoas_1 = hanghoas_1;
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

	@Column(name = "manhom", unique = true, length = 45)
	public String getManhom() {
		return this.manhom;
	}

	public void setManhom(String manhom) {
		this.manhom = manhom;
	}

	@Column(name = "manhomcha")
	public Integer getManhomcha() {
		return this.manhomcha;
	}

	public void setManhomcha(Integer manhomcha) {
		this.manhomcha = manhomcha;
	}

	@Column(name = "tennhom")
	public String getTennhom() {
		return this.tennhom;
	}

	public void setTennhom(String tennhom) {
		this.tennhom = tennhom;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nhomhang")
	public Set<Hanghoa> getHanghoas() {
		return this.hanghoas;
	}

	public void setHanghoas(Set<Hanghoa> hanghoas) {
		this.hanghoas = hanghoas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "nhomhang")
	public Set<Hanghoa> getHanghoas_1() {
		return this.hanghoas_1;
	}

	public void setHanghoas_1(Set<Hanghoa> hanghoas_1) {
		this.hanghoas_1 = hanghoas_1;
	}

}
