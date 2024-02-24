package _com0.storage.assignment.repository;

import _com0.storage.assignment.entiy.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {


}
