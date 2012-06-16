package com.pupuly.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/18/11
 * Time: 4:16 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name="Qrlog")
public class QrQueryLog {

    private Long id;

    private QrImage qrImg;

    // mobile machine code
    private String uuid;

    private User user;

    private Date  queryDate;


    @Id
    @GeneratedValue(strategy=AUTO)
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @ManyToOne(cascade=CascadeType.REFRESH,optional=true)
    @JoinColumn(name = "qrid")
    public QrImage getQrImg() {
        return qrImg;
    }

    public void setQrImg(QrImage qrImg) {
        this.qrImg = qrImg;
    }

    @Column(name="uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @ManyToOne(cascade=CascadeType.REFRESH,optional=true)
    @JoinColumn(name = "userid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name="query_dt")
    public Date getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
    }
}
