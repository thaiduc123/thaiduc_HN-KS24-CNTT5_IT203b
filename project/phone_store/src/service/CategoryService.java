package service;

import dao.CategoryDAO;
import model.Category;

import java.util.List;

public class CategoryService {
    private final CategoryDAO dao = new CategoryDAO();
    public List<Category> getAll() {
        return dao.getAll();
    }

    public boolean add(Category c) {
//        if (dao.existsById(c.getId())) {
//            System.err.println("ID đã tồn tại!");
//            return false;
//        }
//        if (!c.getId().matches("CA\\d{3}")) {
//            System.err.println("ID sai định dạng! (VD: CA001)");
//            return false;
//        }
//        if (dao.existsByName(c.getName())) {
//            System.err.println("Tên đã tồn tại!");
//            return false;
//        }
        return dao.insert(c);
    }
    public boolean update(Category c) {
//        if (!dao.existsById(c.getId())) {
//            System.err.println("ID không tồn tại!");
//            return false;
//        }
        return dao.update(c);
    }
    public boolean delete(String id) {
//        if (!dao.existsById(id)) {
//            System.err.println("ID không tồn tại!");
//            return false;
//        }
        return dao.softDelete(id);
    }
    public boolean existsActiveById(String id) {
        return dao.existsActiveById(id);
    }
}