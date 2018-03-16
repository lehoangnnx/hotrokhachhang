package bcc.springhibernate.service;

import bcc.springhibernate.model.Ungluong;

import java.util.List;

public interface UngLuongService {
    void saveOrUpdate(Ungluong ungluong);
    void deleted(Ungluong ungluong);
    Ungluong findById(Integer id);
    List<Ungluong> findByTrangthaiOrderByIdDesc(String trangthai);
    List<Ungluong> findByTrangthaiNotOrderByIdDesc(String trangthai);
}
