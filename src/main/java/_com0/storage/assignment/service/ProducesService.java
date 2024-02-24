package _com0.storage.assignment.service;

import _com0.storage.assignment.entiy.Products;

import java.util.List;

public interface ProducesService {

    Products saveProducts(Products products);

    List<Products> getAllProducts();

    Products updateProduct(Products products, Long id);


}
