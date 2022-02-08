package nvc.it.springapi.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nvc.it.springapi.model.Product;
import nvc.it.springapi.model.Review;
import nvc.it.springapi.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Object> getAllProduct(){
        List<Product> products = productService.getProducts();
        HashMap<String, Object> map = new HashMap<>();
        map.put("Msg", "Search Success!");
        map.put("data", products);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id){
        HashMap<String, Object> map = new HashMap<>();
        map.put("Msg", "Search By Id Success!");
        map.put("data", productService.findById(id));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getProductByName(@PathVariable String name){
        HashMap<String, Object> map = new HashMap<>();
        map.put("Msg", "Search By Name Success!");
        map.put("data", productService.findProductByName(name));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    //เพิ่มทั้งหมด
    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody Product product){
        HashMap<String, Object> map = new HashMap<>();
        map.put("Msg", "Add Product Success!");
        map.put("data", productService.addProduct(product));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    //อัพเดต
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable String id, @RequestBody Product product){
        HashMap<String, Object> map = new HashMap<>();
        map.put("Msg", "Update Product Success!");
        map.put("data", productService.updateProduct(id, product));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    //เพิ่มบางส่วน
    @PatchMapping("/{id}")
    public ResponseEntity<Object> addReview(@PathVariable String id, @RequestBody Review review){
        HashMap<String, Object> map = new HashMap<>();
        map.put("Msg", "Add Review Success!");
        map.put("data", productService.addReview(id, review));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    //ลบ
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id){
        HashMap<String, Object> map = new HashMap<>();
        if (productService.deleteProduct(id)) {
            map.put("Msg", "Error Delete");
            return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            map.put("Msg", "Delete Success");
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }
    }

}
