package bcc.springhibernate.service;

import bcc.springhibernate.model.Feedback;
import bcc.springhibernate.repository.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	@Override
	public void deleted(Feedback feedback) {
		feedBackRepository.delete(feedback);
		
	}

}
