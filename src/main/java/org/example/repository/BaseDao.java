package org.example.repository;
import java.util.List;

public interface BaseDao<T> {
    List<T> findAll();        // Lấy danh sách tất cả các đối tượng
    boolean add(T t);         // Thêm một đối tượng mới
    T findById(int id);       // Tìm đối tượng theo ID
    boolean update(T t);      // Cập nhật thông tin đối tượng
    boolean delete(int id);   // Xóa đối tượng theo ID
}
