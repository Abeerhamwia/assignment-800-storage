package _com0.storage.assignment.aspect;

import _com0.storage.assignment.entiy.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
public class SaleTransactionEntityListener {

    private static final Logger logger = LoggerFactory.getLogger(SaleTransactionEntityListener.class);


    @PreUpdate
    public void preUpdate(Transaction transaction) {
        // Handle pre-update logic, if needed
        logger.info("Sale transaction pre-update: {}", transaction);
    }
    @PostUpdate
    public void postUpdate(Transaction transaction) {
        // Handle post-update logic, if needed
        logger.info("Sale transaction post-update: {}", transaction);
    }
}
