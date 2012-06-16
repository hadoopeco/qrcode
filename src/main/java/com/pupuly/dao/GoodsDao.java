package com.pupuly.dao;

import com.pupuly.model.QrImage;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/7/11
 * Time: 10:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GoodsDao {

    public void saveAll(List goods);

    public List getCodeList(Long userId);

    public QrImage getCodeByNumiid(String numid);

    public List getCodePaging(Long userId,int firstResult,int maxResults);

    public Long getCodeTotal(Long userId);
}
