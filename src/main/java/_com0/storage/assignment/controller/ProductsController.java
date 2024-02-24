package _com0.storage.assignment.controller;

import _com0.storage.assignment.entiy.Client;
import _com0.storage.assignment.entiy.Products;
import _com0.storage.assignment.exception.ResourceHandlerException;
import _com0.storage.assignment.service.ProducesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProducesService producesService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Products products) {
        Products savedProduct = producesService.saveProducts(products);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts() {
        List<Products> products = producesService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Products products,@PathVariable Long id) {
        try {
            Products updateProduct = producesService.updateProduct(products, id);
            return ResponseEntity.ok(updateProduct);
        } catch (ResourceHandlerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the product not exits " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
