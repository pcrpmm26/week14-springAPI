package nvc.it.springapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nvc.it.springapi.model.Product;
import nvc.it.springapi.model.Review;
import nvc.it.springapi.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(String id){
        return productRepository.findById(id);
    }

    public List<Product> findProductByName(String name){
        return productRepository.findByNameContaining(name);
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(String id, Product product){
        // ดึงข้อมูล Product เดิมจาก Id
        Product currenProduct = productRepository.findById(id).get();
        currenProduct.setName(product.getName());
        currenProduct.setPrice(product.getPrice());
        currenProduct.setUnit_in_stock(product.getUnit_in_stock());
        return Optional.of(productRepository.save(currenProduct));
    }

    public Optional<Product> addReview(String id, Review review){
        Product currenProduct = productRepository.findById(id).get();
        List<Review> reviews = currenProduct.getReviews();
        reviews.add(review);
        currenProduct.setReviews(reviews);
        return Optional.of(productRepository.save(currenProduct));
    }

    public boolean deleteProduct(String id){
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
