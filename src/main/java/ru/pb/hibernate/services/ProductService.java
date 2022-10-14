package ru.pb.hibernate.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.pb.hibernate.dao.ProductDao;
import ru.pb.hibernate.entity.Product;

import java.util.List;

@Component
public class ProductService {

    //@Autowired - не лучший способ
    private ProductDao productDao;


    //Вместо @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public String getTitleById(long id) {
        return productDao.findById(id).getTitle();
    }

    public Product getProduct(long id){
        return productDao.findById(id);
    }
//    @Autowired
//    public void setRepository(ProductRepository inMemoryRepository) {
//        this.inMemoryRepository = inMemoryRepository;
//    }

    public List<Product> getAllProducts(){
        return productDao.findAll();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Доступные товары:\n");
        Product p;
        List<Product> products =productDao.findAll();
        for (int i = 0; i < products.size(); i++) {
            p = products.get(i);
            sb.append(p.getId()).append(" ").append(p.getTitle()).append(", ");
        }
        sb.append("\n");

        return sb.toString();
    }

    public boolean addProduct(long id, String title, int price)
    {
        return productDao.addProduct(id, title, price);
    }


}
