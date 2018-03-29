package bcc.springhibernate.service;

import bcc.springhibernate.model.Quyen;
import bcc.springhibernate.repository.QuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuyenServiceImpl implements QuyenService {

	@Autowired
	QuyenRepository quyenRepository;
	
	@Override
	public List<Quyen> findAll() {
		// TODO Auto-generated method stub
		return quyenRepository.findAll();
	}

	@Override
	public void saveOrUpdate(Quyen quyen) {
		quyenRepository.save(quyen);
	}

	@Override
	public void deleteOne(Integer id) {
		quyenRepository.delete(id);
	}

	@Override
	public Quyen findById(Integer id) {
		
		return quyenRepository.getOne(id);
	}

	@Override
	public Quyen findByTenQuyen(String tenquyen) {
		
		return quyenRepository.findByTenquyen(tenquyen);
	}

	@Override
	public List<Quyen> findByTrangthaiOrderByIdDesc(String trangthai) {
		// TODO Auto-generated method stub
		return quyenRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public Quyen findByMaquyen(String maquyen) {
		
		return quyenRepository.findByMaquyen(maquyen);
	}

	@Override
	public void deleted(Quyen quyen) {
		quyenRepository.delete(quyen);
		
	}

}
