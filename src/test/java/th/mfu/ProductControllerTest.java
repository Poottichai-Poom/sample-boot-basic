// package th.mfu;

// import java.util.Collection;

// import org.junit.Test;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// @SpringBootTest
// public class ProductControllerTest {

//     @Autowired
//     private ProductController controller;

//     //create product and search 
//     @Test
//     public void createAndGet(){
//         //create a product
//         Product product = new Product();
//         product.setName("Dummy Dummy");
//         product.setDescription("123 Main st.");
//         product.setPrice(55.02);

//         ResponseEntity<String> response = controller.createProduct(product);
//         assertEquals(HttpStatus.CREATED, response.getStatusCode());
        
//         //check create
//         ResponseEntity<Collection> res = controller.getAllProduct();
//         assertEquals(11, res.getBody().size());

//         //sreach
//         ResponseEntity<Collection> s = controller.searchByDescription("123 Main");
//         assertEquals(1, s.getBody().size());
//     }
// }
