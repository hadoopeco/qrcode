package com.pupuly.service;

import ch.qos.logback.classic.Logger;
import com.google.zxing.WriterException;
import com.pupuly.model.QrImage;
import com.pupuly.model.User;
import com.pupuly.webapp.action.bean.Item;
import com.pupuly.webapp.action.bean.ItemResponse;
import com.taobao.api.ApiException;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/7/11
 * Time: 8:44 PM
 * To change this template use File | Settings | File Templates.
 */
public interface QrImgService {
    public ItemResponse getOnsale(String topsession,Long userId,Long page,Long size) throws ApiException;

    public List generateTaobaoCode(List<Item> items, User user) throws ApiException, IOException, WriterException, RepositoryException;

    public List gtCodes(Long userId,Integer firstPage,Integer maxResults);

    public String getIds(Long userId);

    public String generateCode(String str, String path) throws WriterException, IOException, RepositoryException;

    public Long getCodeTotal(Long userId);

    public QrImage getGoodsByCode(String code);

    public void generateInternalCode(List<Item> items,User user) throws RepositoryException, IOException, WriterException;
}
