package bcc.springhibernate.service;

import bcc.springhibernate.model.Khachhang;
import bcc.springhibernate.model.Loaikhachhang;
import bcc.springhibernate.model.Nhanvien;
import bcc.springhibernate.model.Nhomkhachhang;
import bcc.springhibernate.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KhachHangServiceImpl implements KhachHangService {

	@Autowired
	KhachHangRepository khachHangRepository;
	
	@Override
	public Khachhang findById(Integer id) {
		
		return khachHangRepository.findById(id);
	}

	@Override
	public Khachhang findByMakh(String makh) {
		
		return khachHangRepository.findByMakh(makh);
	}

	@Override
	public List<Khachhang> findAll() {
		
		return khachHangRepository.findAll();
	}

	@Override
	public List<Khachhang> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return khachHangRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public List<Khachhang> findByTrangthaiNotOrderByIdDesc(String trangthai) {
		
		return khachHangRepository.findByTrangthaiNotOrderByIdDesc(trangthai);
	}

	@Override
	public void saveOrUpdate(Khachhang khachhang) {
		khachHangRepository.save(khachhang);
		
	}

	@Override
	public List<Khachhang> findByNhomkhachhangAndTrangthaiOrderByIdDesc(Nhomkhachhang nhomkhachhang, String trangthai) {
		
		return khachHangRepository.findByNhomkhachhangAndTrangthaiOrderByIdDesc(nhomkhachhang,trangthai);
	}

	@Override
	public List<Khachhang> findByLoaikhachhangAndTrangthaiOrderByIdDesc(Loaikhachhang loaikhachhang, String trangthai) {
		
		return khachHangRepository.findByLoaikhachhangAndTrangthaiOrderByIdDesc(loaikhachhang,trangthai);
	}

	@Override
	public List<Khachhang> findByLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc(Loaikhachhang loaikhachhang,
			Nhomkhachhang nhomkhachhang, String trangthai) {
		
		return khachHangRepository.findByLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc(loaikhachhang,nhomkhachhang,trangthai);
	}

	@Override
	public List<Khachhang> findByTrangthaiOrderByIdDesc(String trangthai, Pageable pageable) {
		
		return khachHangRepository.findByTrangthaiOrderByIdDesc(trangthai, pageable);
	}

	@Override
	public List<Khachhang> findByNhomkhachhangAndTrangthaiOrderByIdDesc(Nhomkhachhang nhomkhachhang, String trangthai,
			Pageable pageable) {
		
		return khachHangRepository.findByNhomkhachhangAndTrangthaiOrderByIdDesc(nhomkhachhang, trangthai, pageable);
	}

	@Override
	public List<Khachhang> findByLoaikhachhangAndTrangthaiOrderByIdDesc(Loaikhachhang loaikhachhang, String trangthai,
			Pageable pageable) {
		
		return khachHangRepository.findByLoaikhachhangAndTrangthaiOrderByIdDesc(loaikhachhang, trangthai, pageable);
	}

	@Override
	public List<Khachhang> findByLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc(Loaikhachhang loaikhachhang,
			Nhomkhachhang nhomkhachhang, String trangthai, Pageable pageable) {
		
		return khachHangRepository.findByLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc(loaikhachhang, nhomkhachhang, trangthai, pageable);
	}

	@Override
	public void deleted(Khachhang khachhang) {
		khachHangRepository.delete(khachhang);
		
	}

	@Override
	public List<Khachhang> findByNhanvienbanhangAndLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc
			(Nhanvien nhanvienByIdnhanvienban, Loaikhachhang loaikhachhang, Nhomkhachhang nhomkhachhang, String trangthai) {
		return khachHangRepository.findByNhanvienbanhangAndLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc
				(nhanvienByIdnhanvienban,loaikhachhang,nhomkhachhang,trangthai);
	}

	@Override
	public List<Khachhang> findByNhanvienbanhangAndLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc(Nhanvien nhanvienByIdnhanvienban, Loaikhachhang loaikhachhang, Nhomkhachhang nhomkhachhang, String trangthai, Pageable pageable) {
		return khachHangRepository.findByNhanvienbanhangAndLoaikhachhangAndNhomkhachhangAndTrangthaiOrderByIdDesc
				(nhanvienByIdnhanvienban,loaikhachhang,nhomkhachhang,trangthai,pageable);
	}

    @Override
    public List<Khachhang> findByNhanvienbanhangLoaikhachhangAndTrangthaiOrderByIdDesc
            (Nhanvien nhanvienByIdnhanvienban, Loaikhachhang loaikhachhang, String trangthai) {
         return khachHangRepository.findByNhanvienbanhangLoaikhachhangAndTrangthaiOrderByIdDesc
                ( nhanvienByIdnhanvienban,  loaikhachhang,  trangthai);
    }

    @Override
    public List<Khachhang> findByNhanvienbanhangLoaikhachhangAndTrangthaiOrderByIdDesc
            (Nhanvien nhanvienByIdnhanvienban, Loaikhachhang loaikhachhang, String trangthai, Pageable pageable) {
         return khachHangRepository.findByNhanvienbanhangLoaikhachhangAndTrangthaiOrderByIdDesc
                ( nhanvienByIdnhanvienban,  loaikhachhang,  trangthai,pageable);
    }

    @Override
    public List<Khachhang> findByNhanvienbanhangNhomkhachhangAndTrangthaiOrderByIdDesc(Nhanvien nhanvienByIdnhanvienban, Nhomkhachhang nhomkhachhang, String trangthai) {
        return khachHangRepository.findByNhanvienbanhangNhomkhachhangAndTrangthaiOrderByIdDesc
                (nhanvienByIdnhanvienban,nhomkhachhang,trangthai);
    }

    @Override
    public List<Khachhang> findByNhanvienbanhangNhomkhachhangAndTrangthaiOrderByIdDesc(Nhanvien nhanvienByIdnhanvienban, Nhomkhachhang nhomkhachhang, String trangthai, Pageable pageable) {
        return khachHangRepository.findByNhanvienbanhangNhomkhachhangAndTrangthaiOrderByIdDesc
                (nhanvienByIdnhanvienban,nhomkhachhang,trangthai,pageable);
    }

    @Override
    public List<Khachhang> findByNhanvienbanhangTrangthaiOrderByIdDesc(Nhanvien nhanvienByIdnhanvienban, String trangthai) {
        return khachHangRepository.findByNhanvienbanhangTrangthaiOrderByIdDesc
                (nhanvienByIdnhanvienban,trangthai);
    }

    @Override
    public List<Khachhang> findByNhanvienbanhangTrangthaiOrderByIdDesc(Nhanvien nhanvienByIdnhanvienban, String trangthai, Pageable pageable) {
        return khachHangRepository.findByNhanvienbanhangTrangthaiOrderByIdDesc
                (nhanvienByIdnhanvienban,trangthai,pageable);
    }
}
