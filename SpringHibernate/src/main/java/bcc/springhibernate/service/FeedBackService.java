package bcc.springhibernate.service;

import bcc.springhibernate.model.Feedback;

import java.util.List;

public interface FeedBackService {
	void saveOrUpdate(Feedback feedback);
	void deleted(Feedback feedback);
	List<Feedback> findAll();
	List<Feedback> findByTrangthaiOrderByIdDesc(String trangthai);	
	Feedback findById(Integer id);
}
