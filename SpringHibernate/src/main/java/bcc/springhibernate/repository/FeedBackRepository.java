package bcc.springhibernate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bcc.springhibernate.model.Feedback;

@Repository
public interface FeedBackRepository extends JpaRepository<Feedback, Integer> {
	List<Feedback> findAll();
	List<Feedback> findByTrangthaiOrderByIdDesc(String trangthai);	
	Feedback findById(Integer id);
}
