package bcc.springhibernate.service;

import java.util.List;

import bcc.springhibernate.model.Feedback;

public interface FeedBackService {
	void saveOrUpdate(Feedback feedback);
	List<Feedback> findAll();
	List<Feedback> findByTrangthaiOrderByIdDesc(String trangthai);	
	Feedback findById(Integer id);
}
