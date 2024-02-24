package _com0.storage.assignment.entiy;


import _com0.storage.assignment.extra.ProductCategory;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    String name;

    String description;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Products() {
    }

    public Products(String name, String description, ProductCategory productCategory, Date creationDate) {
        this.name = name;
        this.description = description;
        this.productCategory = productCategory;
        this.creationDate = creationDate;
    }


    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }
}
