package _com0.storage.assignment.extra.dto;

import java.util.List;

public class NewSaleDTO {

    private Long clientId;
    private String seller;
    private List<NewTransactionDTO> transactions;


    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public List<NewTransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<NewTransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
