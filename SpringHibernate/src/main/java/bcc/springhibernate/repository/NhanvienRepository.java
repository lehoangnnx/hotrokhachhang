package bcc.springhibernate.repository;

import bcc.springhibernate.model.Nhanvien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanvienRepository extends JpaRepository<Nhanvien, Integer> {

    Nhanvien getById(Integer id);
}
