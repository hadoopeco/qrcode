package com.pupuly.service.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.pupuly.dao.GoodsDao;
import com.pupuly.dao.ProductDao;
import com.pupuly.model.Product;
import com.pupuly.model.QrImage;
import com.pupuly.model.User;
import com.pupuly.service.DocService;
import com.pupuly.service.QrImgService;
import com.pupuly.service.QRcodeGenerator;
import com.pupuly.webapp.action.bean.Item;
import com.pupuly.webapp.action.bean.ItemResponse;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.ItemsListGetRequest;
import com.taobao.api.request.ItemsOnsaleGetRequest;
import com.taobao.api.response.ItemsListGetResponse;
import com.taobao.api.response.ItemsOnsaleGetResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import javax.jcr.RepositoryException;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/7/11
 * Time: 8:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class QrImgServiceImpl implements QrImgService {

    private static Log logger = LogFactory.getLog(QrImgServiceImpl.class);

    private TaobaoClient client;

    private String prefixUrl;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private DocService docService;
//    private JcrOperations jcrTemplate;
    private QRcodeGenerator generator;
    private Writer multiFormatWriter;

//    public void setJcrTemplate(JcrOperations jcrTemplate) {
//        this.jcrTemplate = jcrTemplate;
//    }


    public void setPrefixUrl(String prefixUrl) {
        this.prefixUrl = prefixUrl;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setDocService(DocService docService) {
        this.docService = docService;
    }

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void setMultiFormatWriter(Writer multiFormatWriter) {
        this.multiFormatWriter = multiFormatWriter;
    }

    public void setGenerator(QRcodeGenerator generator) {
        this.generator = generator;
    }

    public void setClient(TaobaoClient client) {
        this.client = client;
    }

    public ItemResponse getOnsale(String topsession,Long userId,Long page,Long size) throws ApiException {
        logger.debug("QrImgServiceImpl.getOnsale begin");
        ItemsOnsaleGetRequest request = new ItemsOnsaleGetRequest();
        request.setFields("num_iid,title,nick,pic_url");
        request.setPageNo(page);
        request.setPageSize(size);
        ItemsOnsaleGetResponse response = client.execute(request,topsession);
        logger.debug("QrImgServiceImpl.getOnsale get response from taobao");
        List<com.taobao.api.domain.Item> items = response.getItems();
        String exsitId = getIds(userId);
        logger.debug("QrImgServiceImpl.getOnsale get the exsit ids ="+exsitId);
        List filter = new ArrayList();
        Item item;
        for(com.taobao.api.domain.Item it:items){
            item = new Item();
            item.setId(String.valueOf(it.getNumIid()));
            item.setTitle(it.getTitle());
            item.setImgUrl(it.getPicUrl());
            if(exsitId.contains(String.valueOf(it.getNumIid()))){
                logger.debug("QrImgServiceImpl.getOnsale filter a item id="+it.getNumIid());
                item.setExist(true);
            }
            filter.add(item);
        }
        ItemResponse res = new ItemResponse();
        res.setItems(filter);
        res.setTotal(response.getTotalResults());
        logger.debug("QrImgServiceImpl.getOnsale  end");
        return res;
    }

    public List generateTaobaoCode(List<Item> items, User user) throws ApiException, IOException, WriterException, RepositoryException {
        String exsitId = getIds(user.getId());
        String ids = composeNumid(items, exsitId);
        if(!StringUtils.isEmpty(ids)){
            List response = getGoodsList(ids);
            saveGoodsBatch(response,user);
        }
        return null;
    }

    public Long getCodeTotal(Long userId){
        return  goodsDao.getCodeTotal(userId);
    }

    public List gtCodes(Long userId,Integer firstPage,Integer maxResults){
        return goodsDao.getCodePaging(userId, firstPage, maxResults);
    }
    public String getIds(Long userId){
        List list = goodsDao.getCodeList(userId);
        QrImage qrcode;
        StringBuffer sb = new StringBuffer("");
        if(list!=null){
            for (Object obj : list) {
                qrcode = (QrImage)obj;
                sb.append(qrcode.getNumiid()).append(",");
            }
        }

        return sb.toString();
    }

    private String composeNumid(List<Item> items,String ids){
        StringBuffer sb = new StringBuffer();

        for(Item item : items){
            if(item != null && item.getGen() != null && !ids.contains(item.getId())){
                sb.append(item.getId());
                sb.append(",");
            }
        }
        if(sb.length()>1){
            sb.deleteCharAt(sb.length()-1);
        }

        return sb.toString();
    }


    public List  getGoodsList(String ids) throws ApiException {
        ItemsListGetRequest req=new ItemsListGetRequest();
        req.setNumIids(ids);
        req.setFields("num_iid,detail_url,title,nick,pic_url,cid,price,type,delist_time,post_fee,score,volume");
        ItemsListGetResponse response = client.execute(req);
        return response.getItems();
    }


    public String generateCode(String str, String path) throws WriterException, IOException, RepositoryException {
        BitMatrix bitMatrix = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE,200,200);
        ByteArrayOutputStream  out = new ByteArrayOutputStream();
        generator.writeToStream(bitMatrix, "png", out);
        InputStream in = new ByteArrayInputStream(out.toByteArray());
        return docService.saveNode(path,in);
    }



    private List saveGoodsBatch(List<com.taobao.api.domain.Item> items,User user) throws IOException, WriterException, RepositoryException {
        logger.debug("QrImgServiceImpl.saveGoodsBatch begins");
        List<QrImage> goods = new ArrayList<QrImage>();
        QrImage good;
        String str;
        String docIdentify;
        for(com.taobao.api.domain.Item item:items){
            if(item != null ){
                good = new QrImage();
                str = String.valueOf(item.getNumIid());
                good.setNumiid(str);
                good.setName(item.getTitle());
                good.setUser(user);
                good.setGoodsUrl(item.getDetailUrl());
                docIdentify = generateCode(prefixUrl+str,crateDocPath(item,user));
                good.setDocId(docIdentify);
                good.setCreated_dt(new Date());
                goods.add(good);
            }
        }
        if(!goods.isEmpty()){
            goodsDao.saveAll(goods);
        }
        logger.debug("QrImgServiceImpl.saveGoodsBatch end");
        return goods;
    }



    public QrImage getGoodsByCode(String code){
       logger.debug("QrImgServiceImpl.getGoodsByCode begin");
       QrImage img = goodsDao.getCodeByNumiid(code);
       logger.debug("QrImgServiceImpl.getGoodsByCode end");
       return img;
    }

    public void generateInternalCode(List<Item> items,User user) throws RepositoryException, IOException, WriterException {
        List list = productDao.getUserProductByids(user.getId(),composeNumid(items,""));
        saveInterBatch(list,user);
    }



    private List<QrImage> saveInterBatch(List<Product> products, User user) throws RepositoryException, IOException, WriterException {
        logger.debug("QrImgServiceImpl.saveGoodsBatch begins");
        List<QrImage> qrs = new ArrayList<QrImage>();
        QrImage qr;
        String str;
        String docIdentify;
        for(Product product:products){
            if(product != null ){
                qr = new QrImage();
                str = passwordEncoder.encodePassword(String.valueOf(new Date().getTime()),"SHA");
                qr.setNumiid(str);
                qr.setName(product.getProductName());
                qr.setUser(user);
                qr.setAccesscount(0);
                qr.setGoodsUrl(product.getProductUrl());
                docIdentify = generateCode(prefixUrl+str,createInternalDocPath(user,str));
                qr.setDocId(docIdentify);
                qr.setCreated_dt(new Date());
                qrs.add(qr);
                product.setExisted(true);
                productDao.save(product);
            }
        }
        if(!qrs.isEmpty()){
            goodsDao.saveAll(qrs);
        }

        logger.debug("QrImgServiceImpl.saveGoodsBatch end");
        return qrs;
    }

    // create the internal doc path
    private String createInternalDocPath(User user,String productId){
        logger.debug("QrImgServiceImpl.createInternalDocPath begin");
        StringBuffer sb = new StringBuffer("internal/");
        sb.append(user.getId()).append("/");
        sb.append(productId).append("/");
        logger.debug("QrImgServiceImpl.createInternalDocPath doc path="+sb.toString());
        logger.debug("QrImgServiceImpl.createInternalDocPath end");
        return sb.toString();
    }

    private String crateDocPath(com.taobao.api.domain.Item item,User user){
        logger.debug("QrImgServiceImpl.crateDocPath begin");
        StringBuffer sb = new StringBuffer("taobao/");
        sb.append(user.getId()).append("/");
        sb.append(item.getCid()).append("/");
        sb.append(item.getNumIid()).append("/");
        logger.debug("QrImgServiceImpl.crateDocPath doc path="+sb.toString());
        logger.debug("QrImgServiceImpl.crateDocPath end");
        return sb.toString();
    }

}
