package _com0.storage.assignment.controller;

import _com0.storage.assignment.entiy.Sales;
import _com0.storage.assignment.exception.ResourceHandlerException;
import _com0.storage.assignment.extra.dto.NewSaleDTO;
import _com0.storage.assignment.extra.dto.UpdateTransactionDTO;
import _com0.storage.assignment.service.SalesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    SalesService salesService;

    @Autowired
    ObjectMapper objectMapper;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Sales sales) {
        Sales savedSales = salesService.createSale(sales);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSales);
    }

    @GetMapping("/getAllSales")
    public ResponseEntity<?> getAllSales() {
        List<Sales> sales = salesService.getAllSale();
        return ResponseEntity.status(HttpStatus.OK).body(sales);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Sales sales,@PathVariable Long id) {
        try {
            Sales updateSales = salesService.updateSale(sales, id);
            return ResponseEntity.ok(updateSales);
        } catch (ResourceHandlerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("the Sales not exits " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    @PostMapping("/create-with-transactions")
    public ResponseEntity<?> createSaleWithTransactions(@RequestBody NewSaleDTO newSaleDTO) {
        try {
            salesService.createSaleWithTransactions(newSaleDTO);
            return ResponseEntity.ok("New sale created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create new sale.");
        }
    }

    @PutMapping("/{saleId}/transactions/update")
    public ResponseEntity<String> updateTransaction(@PathVariable Long saleId, @RequestBody UpdateTransactionDTO updateTransactionDTO) {
        try {
            salesService.updateTransactionQuantityAndPrice(saleId, updateTransactionDTO);
            return ResponseEntity.ok("Transaction updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update transaction.");
        }
    }
}
