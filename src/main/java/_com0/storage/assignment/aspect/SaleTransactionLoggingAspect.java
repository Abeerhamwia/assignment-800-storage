package _com0.storage.assignment.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SaleTransactionLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(SaleTransactionEntityListener.class);


    @Pointcut("execution(* _com0.storage.assignment.entiy.Transaction.update*(..))")
    public void transactionUpdatePointcut() {}

    @AfterReturning(pointcut = "transactionUpdatePointcut()", returning = "result")
    public void logTransactionUpdate(Object result) {
        // Log the update operation on sale transactions
        logger.info("Sale transaction pre-update: {}", result);
    }
}
