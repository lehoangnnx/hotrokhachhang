package bcc.springhibernate.model;
// Generated Jan 9, 2018 8:25:15 PM by Hibernate Tools 5.1.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Feedback generated by hbm2java
 */
@Entity
@Table(name = "feedback", catalog = "hotrobanhang")
public class Feedback implements java.io.Serializable {

	private Integer id;
	private Integer hanghoaId;
	private Integer nhanvienId;
	private Integer nhavientaoId;
	private String noidung;
	private Date ngaytao;
	private String trangthai;
	private String ghichu;

	public Feedback() {
	}

	public Feedback(Integer hanghoaId, Integer nhanvienId, Integer nhavientaoId, String noidung, Date ngaytao,
			String trangthai, String ghichu) {
		this.hanghoaId = hanghoaId;
		this.nhanvienId = nhanvienId;
		this.nhavientaoId = nhavientaoId;
		this.noidung = noidung;
		this.ngaytao = ngaytao;
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

	@Column(name = "hanghoa_id")
	public Integer getHanghoaId() {
		return this.hanghoaId;
	}

	public void setHanghoaId(Integer hanghoaId) {
		this.hanghoaId = hanghoaId;
	}

	@Column(name = "nhanvien_id")
	public Integer getNhanvienId() {
		return this.nhanvienId;
	}

	public void setNhanvienId(Integer nhanvienId) {
		this.nhanvienId = nhanvienId;
	}

	@Column(name = "nhavientao_id")
	public Integer getNhavientaoId() {
		return this.nhavientaoId;
	}

	public void setNhavientaoId(Integer nhavientaoId) {
		this.nhavientaoId = nhavientaoId;
	}

	@Column(name = "noidung")
	public String getNoidung() {
		return this.noidung;
	}

	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ngaytao", length = 19)
	public Date getNgaytao() {
		return this.ngaytao;
	}

	public void setNgaytao(Date ngaytao) {
		this.ngaytao = ngaytao;
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
