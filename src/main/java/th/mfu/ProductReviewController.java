package th.mfu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductReviewController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CustomerRepository custoRepo;

    @Autowired
    private ProductReviewRepository productreviewRepo;

    @GetMapping("/Productreviews")
    public ResponseEntity<List<ProductReview>> getAllReviews() {
        List<ProductReview> reviews = productreviewRepo.findAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/Productreviews")
    public ResponseEntity<ProductReview> createReview(@RequestBody ProductReview request) {
        ProductReview review = new ProductReview();
        review.setRating(request.getRating());
        review.setComments(request.getComments());

        Customer customer = custoRepo.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        review.setCustomer(customer);
        review.setProduct(product);

        ProductReview savedReview = productreviewRepo.save(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

}
