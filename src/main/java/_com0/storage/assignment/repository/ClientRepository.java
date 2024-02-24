package _com0.storage.assignment.repository;

import _com0.storage.assignment.entiy.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
