package com.pupuly.webapp.action.bean;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/7/11
 * Time: 7:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Item {

    private String id;
    private Boolean gen;
    private String title;
    private String imgUrl;
    private Boolean exist;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Boolean getGen() {
        return gen;
    }

    public void setGen(Boolean gen) {
        this.gen = gen;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }
}
