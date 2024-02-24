package _com0.storage.assignment.service.Impl;

import _com0.storage.assignment.entiy.Products;
import _com0.storage.assignment.exception.ResourceHandlerException;
import _com0.storage.assignment.repository.ProductsRepository;
import _com0.storage.assignment.service.ProducesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductsServiceImpl implements ProducesService {

    @Autowired
    ProductsRepository productsRepository;


    @Override
    public Products saveProducts(Products products) {
        return productsRepository.save(products);
    }

    @Override
    public List<Products> getAllProducts() {
        try {
            return productsRepository.findAll();
        } catch (Exception ex) {
            log.error("Error occurred while fetching all products", ex);
            throw new RuntimeException("An error occurred while fetching all products", ex);
        }
    }

    @Override
    public Products updateProduct(Products products, Long id) {
// we need to check whether Client with given id is exists in DB or not
        Optional<Products> productsOptional = productsRepository.findById(id);
        if (productsOptional.isPresent()) {
            // Retrieve the existing client from the Optional
            Products existingProduct = productsOptional.get();

            // Update only the fields that are not null in the updated client
            if (products.getName() != null) {
                existingProduct.setName(products.getName());
            }
            if (products.getDescription() != null) {
                existingProduct.setDescription(products.getDescription());
            }
            if (products.getProductCategory() != null) {
                existingProduct.setProductCategory(products.getProductCategory());
            }
            if (products.getCreationDate() != null) {
                existingProduct.setCreationDate(products.getCreationDate());
            }
            // Save the updated client
            return productsRepository.save(existingProduct);
        } else {
            // If the client with the given ID does not exist, throw an exception or handle it appropriately
            throw new ResourceHandlerException("Client", "Id", id);
        }
    }


}
