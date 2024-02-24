package _com0.storage.assignment.service;

import _com0.storage.assignment.entiy.Sales;
import _com0.storage.assignment.entiy.Transaction;
import _com0.storage.assignment.extra.dto.NewSaleDTO;
import _com0.storage.assignment.extra.dto.UpdateTransactionDTO;

import java.util.List;

public interface SalesService {

    Sales createSale(Sales sale);

    List<Sales> getAllSale();

    Sales updateSale(Sales sale, Long id);

    Sales createSaleWithTransactions(NewSaleDTO newSaleDTO);

    Transaction updateTransactionQuantityAndPrice(Long saleId, UpdateTransactionDTO updateTransactionDTO);

}
