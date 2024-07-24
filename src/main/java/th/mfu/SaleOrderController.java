package th.mfu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SaleOrderController {

    @Autowired
    SaleOrderRepository orderRepo;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    ProductRepository productRepo;

    // POST for creating an order
    @PostMapping("/customers/{customerId}/orders")
    public ResponseEntity<String> createOrder(@PathVariable Long customerId, @RequestBody SaleOrder order) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
            order.setCustomer(customer.get());
            orderRepo.save(order);
            return new ResponseEntity<String>("Order created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

    // GET for getting orders by customer
    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<Collection<SaleOrder>> getOrdersByCustomer(@PathVariable Long customerId) {
        System.out.println("getOrdersByCustomer is called");
        Optional<Customer> optCustomer = customerRepo.findById(customerId);
        if (!optCustomer.isPresent()) {
            return new ResponseEntity<Collection<SaleOrder>>(HttpStatus.NOT_FOUND);
        }
        System.out.println(optCustomer.get().getSaleOrders().size());
        Collection<SaleOrder> orders = orderRepo.findByCustomerId(customerId);
        return new ResponseEntity<Collection<SaleOrder>>(orders, HttpStatus.OK);
    }


    // GET for getting all orders
    @GetMapping("/orders")
    public ResponseEntity<Collection<SaleOrder>> getAllOrders() {
        System.out.println("getAllorder called...");
        return new ResponseEntity<Collection<SaleOrder>>(orderRepo.findAll(), HttpStatus.OK);
    }


}
