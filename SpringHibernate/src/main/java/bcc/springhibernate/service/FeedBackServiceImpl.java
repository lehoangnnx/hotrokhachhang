package bcc.springhibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcc.springhibernate.model.Feedback;
import bcc.springhibernate.repository.FeedBackRepository;

@Service
public class FeedBackServiceImpl implements FeedBackService{
@Autowired
FeedBackRepository feedBackRepository;
	@Override
	public List<Feedback> findAll() {
		
		return feedBackRepository.findAll();
	}

	@Override
	public List<Feedback> findByTrangthaiOrderByIdDesc(String trangthai) {
		
		return feedBackRepository.findByTrangthaiOrderByIdDesc(trangthai);
	}

	@Override
	public Feedback findById(Integer id) {
		
		return feedBackRepository.findById(id);
	}

	@Override
	public void saveOrUpdate(Feedback feedback) {
		feedBackRepository.save(feedback);
		
	}

}
