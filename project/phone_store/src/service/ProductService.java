package service;

import dao.ProductDAO;
import model.Product;

import java.util.List;

public class ProductService {
    private ProductDAO dao = new ProductDAO();
//    public String generateId() {
//        String lastId = dao.getLastId();
//        if (lastId == null) {
//            return "PA001";
//        }
//        int n = Integer.parseInt(lastId.substring(2));
//        n++;
//        return String.format("PA%03d", n);
//    }
public boolean add(Product p) {
//    if (!p.getId().matches("PA\\d{5}")) {
//        System.err.println("ID sai định dạng! (VD: PA00001)");
//        return false;
//    }
//    if (dao.findById(p.getId()) != null) {
//        System.err.println("ID đã tồn tại!");
//        return false;
//    }
//    if (!new CategoryService().existsActiveById(p.getCategoryId())) {
//        System.err.println("Category không tồn tại!");
//        return false;
//    }
//    if (p.getPrice() <= 0 || p.getStock() <= 0) {
//        System.err.println("Giá và số lượng phải > 0");
//        return false;
//    }
    return dao.insert(p);
}
    public boolean update(Product p) {
//        if (!p.getId().matches("PA\\d{5}")) {
//            System.err.println("ID không hợp lệ!");
//            return false;
//        }
//        if (!new CategoryService().existsActiveById(p.getCategoryId())) {
//            System.err.println("Category không tồn tại!");
//            return false;
//        }
//        if (p.getPrice() <= 0 || p.getStock() <= 0) {
//            System.err.println("Giá và số lượng phải > 0");
//            return false;
//        }
        return dao.update(p);
    }
    public Product findById(String id) {
        return dao.findById(id);
    }
    public boolean delete(String id) {
        return dao.delete(id);
    }

    public List<Product> search(String keyword) {
        return dao.searchByName(keyword);
    }

    public List<Product> getAll() {
        return dao.getAll();
    }
    public List<Product> getAvailableProducts() {
        return dao.getAvailableProducts();
    }
}