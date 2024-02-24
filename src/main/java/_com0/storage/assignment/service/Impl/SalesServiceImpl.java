package _com0.storage.assignment.service.Impl;

import _com0.storage.assignment.entiy.Client;
import _com0.storage.assignment.entiy.Products;
import _com0.storage.assignment.entiy.Sales;
import _com0.storage.assignment.entiy.Transaction;
import _com0.storage.assignment.exception.ResourceHandlerException;
import _com0.storage.assignment.extra.dto.NewSaleDTO;
import _com0.storage.assignment.extra.dto.NewTransactionDTO;
import _com0.storage.assignment.extra.dto.UpdateTransactionDTO;
import _com0.storage.assignment.repository.ClientRepository;
import _com0.storage.assignment.repository.ProductsRepository;
import _com0.storage.assignment.repository.SalesRepository;
import _com0.storage.assignment.repository.TransactionRepository;
import _com0.storage.assignment.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    SalesRepository salesRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Sales createSale(Sales sale) {
        // Calculate total before saving
        sale.setTotal(sale.getTotal());
        return salesRepository.save(sale);
    }

    @Override
    public List<Sales> getAllSale() {
        try {
            List<Sales> results = salesRepository.findAll();
            return results;
        } catch (Exception ex) {
            log.error("Error occurred while fetching all sales", ex);
            throw new RuntimeException("An error occurred while fetching all sales", ex);
        }
    }

    @Override
    public Sales updateSale(Sales sale, Long saleId) {
        Optional<Sales> optionalSale = salesRepository.findById(saleId);
        if (optionalSale.isPresent()) {
            Sales existingSale = optionalSale.get();

            // Update sale details
            if (sale.getCreationDate() != null) {
                existingSale.setCreationDate(sale.getCreationDate());
            }
            if (sale.getClient() != null) {
                Client client = sale.getClient();
                Optional<Client> optionalClient = clientRepository.findById(client.getId());
                if (optionalClient.isPresent()) {
                    existingSale.setClient(client);
                } else {
                    throw new ResourceHandlerException("Client", "clientId", client.getId());
                }
            }
            if (sale.getSeller() != null) {
                existingSale.setSeller(sale.getSeller());
            }

            // Recalculate total before updating
            sale.setTotal(sale.getTotal());

            // Save and return updated sale
            return salesRepository.save(sale);
        } else {
            throw new ResourceHandlerException("sales", "Id", saleId);
        }

    }

    @Override
    public Sales createSaleWithTransactions(NewSaleDTO newSaleDTO) {
        // Retrieve client by ID
        Client client = clientRepository.findById(newSaleDTO.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        // Create new sale
        Sales sale = new Sales();
        sale.setClient(client);
        sale.setSeller(newSaleDTO.getSeller());

        // Create transactions
        List<Transaction> transactions = new ArrayList<>();
        for (NewTransactionDTO transactionDTO : newSaleDTO.getTransactions()) {
            Products product = productsRepository.findById(transactionDTO.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            Transaction transaction = new Transaction(product, sale, transactionDTO.getQuantity(), transactionDTO.getPrice());
            transactions.add(transaction);
        }

        sale.setTransactions(transactions);

        // Calculate total
        sale.setTotal(sale.getTotal());

        // Save sale
        return salesRepository.save(sale);
    }

    @Override
    public Transaction updateTransactionQuantityAndPrice(Long saleId, UpdateTransactionDTO updateTransactionDTO) {
        Sales sale = salesRepository.findById(saleId)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found"));

        Transaction transaction = transactionRepository.findById(updateTransactionDTO.getTransactionId())
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        // Update quantity and price
        transaction.setQuantity(updateTransactionDTO.getNewQuantity());
        transaction.setPrice(updateTransactionDTO.getNewPrice());

        // Recalculate total
        sale.setTotal(sale.getTotal());

        // Save changes
        salesRepository.save(sale);
        return transactionRepository.save(transaction);
    }

}
