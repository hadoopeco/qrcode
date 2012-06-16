package com.pupuly.model;

import com.pupuly.model.BaseObject;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableComponent;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.GeneratedValue;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="goods")
@Searchable
@XmlRootElement
public class QrImage extends BaseObject implements Serializable {
    private Long id;
    private String docId;
    private String goodsUrl;
    private String name;
    private String numiid;
    private User user;
    private Integer accesscount;
    private Date created_dt;

    @Id @GeneratedValue(strategy=AUTO)
    @SearchableId
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    //Qr image file id
    @Column(name="docId")
    @SearchableProperty
    public String getDocId() {
        return this.docId;
    }
    
    public void setDocId(String docId) {
        this.docId = docId;
    }
    
    @Column(name="goodsUrl")
    @SearchableProperty
    public String getGoodsUrl() {
        return this.goodsUrl;
    }
    
    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }
    
    @Column(name="name")
    @SearchableProperty
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="numiid")
    @SearchableProperty
    public String getNumiid() {
        return this.numiid;
    }
    
    public void setNumiid(String numiid) {
        this.numiid = numiid;
    }
    
    @ManyToOne(cascade=CascadeType.REFRESH,optional=true)
    @JoinColumn(name = "userId")
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @Column(name="access")
    public Integer getAccesscount() {
        return accesscount;
    }

    public void setAccesscount(Integer accesscount) {
        this.accesscount = accesscount;
    }

    @Column(name="created_dt")
    public Date getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(Date created_dt) {
        this.created_dt = created_dt;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QrImage pojo = (QrImage) o;

        if (docId != null ? !docId.equals(pojo.docId) : pojo.docId != null) return false;
        if (goodsUrl != null ? !goodsUrl.equals(pojo.goodsUrl) : pojo.goodsUrl != null) return false;
        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (numiid != null ? !numiid.equals(pojo.numiid) : pojo.numiid != null) return false;
        if (user != null ? !user.equals(pojo.user) : pojo.user != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (goodsUrl != null ? goodsUrl.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (numiid != null ? numiid.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("docId").append("='").append(getDocId()).append("', ");
        sb.append("goodsUrl").append("='").append(getGoodsUrl()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("numiid").append("='").append(getNumiid()).append("', ");
        sb.append("user").append("='").append(getUser()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
