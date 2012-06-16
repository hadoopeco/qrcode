package com.pupuly.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/22/11
 * Time: 6:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="product")
public class Product {

    private Long id;
    private String productName;
    private String productUrl;
    private User user;
    private Date addDt;
    private boolean existed;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(name="product_url")
    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    @Column(name="created_dt")
    public Date getAddDt() {
        return addDt;
    }

    public void setAddDt(Date addDt) {
        this.addDt = addDt;
    }

    @Column(name="existed")
    public boolean isExisted() {
        return existed;
    }

    public void setExisted(boolean existed) {
        this.existed = existed;
    }
}
