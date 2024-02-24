package _com0.storage.assignment.entiy;

import _com0.storage.assignment.aspect.SaleTransactionEntityListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "transactions")
@EntityListeners(SaleTransactionEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Products product;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonIgnore
    private Sales sale;

    private int quantity;

    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Sales getSale() {
        return sale;
    }

    public void setSale(Sales sale) {
        this.sale = sale;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Transaction() {
    }

    public Transaction(Products product, Sales sale, int quantity, double price) {
        this.product = product;
        this.sale = sale;
        this.quantity = quantity;
        this.price = price;
    }
}
