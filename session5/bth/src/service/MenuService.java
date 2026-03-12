package service;

import model.MenuItem;
import repository.MenuRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MenuService {

    private MenuRepository repo;

    public MenuService(MenuRepository repo) {
        this.repo = repo;
    }

    // THÊM
    public void addMenuItem(MenuItem item){
        repo.add(item);
    }

    // TÌM THEO ID
    public Optional<MenuItem> findById(String id){
        return repo.getAll()
                .stream()
                .filter(i -> i.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    // SỬA
    public boolean updateMenuItem(String id, String newName, double newPrice){

        Optional<MenuItem> itemOpt = findById(id);

        if(itemOpt.isPresent()){
            MenuItem item = itemOpt.get();
            item.setName(newName);
            item.setPrice(newPrice);
            return true;
        }

        return false;
    }

    // XÓA
    public boolean deleteById(String id){

        Optional<MenuItem> itemOpt = findById(id);

        if(itemOpt.isPresent()){
            repo.getAll().remove(itemOpt.get());
            return true;
        }

        return false;
    }

    // TÌM THEO TÊN
    public List<MenuItem> searchByName(String name){
        return repo.getAll()
                .stream()
                .filter(i -> i.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // LỌC THEO GIÁ
    public List<MenuItem> filterByPrice(double min, double max){
        return repo.getAll()
                .stream()
                .filter(i -> i.getPrice() >= min && i.getPrice() <= max)
                .collect(Collectors.toList());
    }

    // HIỂN THỊ TẤT CẢ
    public List<MenuItem> getAll(){
        return repo.getAll();
    }
}