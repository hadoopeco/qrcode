package com.pupuly.webapp.action.bean;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/17/11
 * Time: 9:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemResponse {
    private List items;
    private Long total;

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
