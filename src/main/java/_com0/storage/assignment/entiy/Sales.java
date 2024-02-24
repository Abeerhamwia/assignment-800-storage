package _com0.storage.assignment.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @ManyToOne
    private Client client;

    private String seller;

    private double total;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
       this.total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getPrice() * transaction.getQuantity();
        }
        return total;
    }

    public Sales() {
    }

    public Sales(Date creationDate, Client client, String seller, double total, List<Transaction> transactions) {
        this.creationDate = creationDate;
        this.client = client;
        this.seller = seller;
        this.total = total;
        this.transactions = transactions;
    }


    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }
}
