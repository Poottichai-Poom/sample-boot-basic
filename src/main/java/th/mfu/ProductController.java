package th.mfu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductRepository ProductRepo;

    @GetMapping("/Products")
    public ResponseEntity<Collection> getAllProduct() {
        Collection results = ProductRepo.findAll();
        return new ResponseEntity<Collection>(results, HttpStatus.OK);
    }

    @GetMapping("/Products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
            if (ProductRepo.existsById(id)){
               Optional <Product> foundProduct = ProductRepo.findById(id);
                return new ResponseEntity<Product>(foundProduct.get(), HttpStatus.OK);
            }else {
                return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
            }
            
    }

    @GetMapping("/Products/description/{prefix}")
    public ResponseEntity<Collection> searchByDescription(@PathVariable String prefix){
                return new ResponseEntity<Collection>(ProductRepo.findByDescription(prefix), HttpStatus.OK);
    }

    @GetMapping("/Products/price")
    public ResponseEntity<Collection> searchByPrice(){
          return new ResponseEntity<Collection>(ProductRepo.findByOrderByPrice(), HttpStatus.OK);
    }    
    
    @PostMapping("/Products")
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        ProductRepo.save(product);
        return new ResponseEntity<String>("Product created", HttpStatus.CREATED);

    }
    @DeleteMapping("/Products/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id){
        ProductRepo.deleteById(id);
        return new ResponseEntity<String>("Product deleted", HttpStatus.NO_CONTENT);
    }
}
