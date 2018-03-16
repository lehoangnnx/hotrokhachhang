package bcc.springhibernate.service;

import bcc.springhibernate.model.Ungluong;
import bcc.springhibernate.repository.UngLuongReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UngLuongServiceImpl implements UngLuongService {
    @Autowired
    UngLuongReposiotry ungLuongReposiotry;

    @Override
    public void saveOrUpdate(Ungluong ungluong) {
        ungLuongReposiotry.save(ungluong);
    }

    @Override
    public void deleted(Ungluong ungluong) {
        ungLuongReposiotry.delete(ungluong);
    }

    @Override
    public Ungluong findById(Integer id) {
        return ungLuongReposiotry.findById(id);
    }

    @Override
    public List<Ungluong> findByTrangthaiOrderByIdDesc(String trangthai) {
        return ungLuongReposiotry.findByTrangthaiOrderByIdDesc(trangthai);
    }

    @Override
    public List<Ungluong> findByTrangthaiNotOrderByIdDesc(String trangthai) {
        return ungLuongReposiotry.findByTrangthaiNotOrderByIdDesc(trangthai);
    }
}
