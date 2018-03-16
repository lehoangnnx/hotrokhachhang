package bcc.springhibernate.repository;

import bcc.springhibernate.model.Ungluong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UngLuongReposiotry extends JpaRepository<Ungluong,Integer> {
    List<Ungluong> findByTrangthaiOrderByIdDesc(String trangthai);
    List<Ungluong> findByTrangthaiNotOrderByIdDesc(String trangthai);

    Ungluong findById(Integer id);
}
