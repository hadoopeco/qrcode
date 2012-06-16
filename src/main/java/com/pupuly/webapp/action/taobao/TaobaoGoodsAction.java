package com.pupuly.webapp.action.taobao;

import com.google.zxing.WriterException;
import com.pupuly.model.QrImage;
import com.pupuly.service.DocService;
import com.pupuly.service.QrImgService;
import com.pupuly.webapp.action.BaseAction;
import com.pupuly.webapp.action.bean.Item;
import com.pupuly.webapp.action.bean.ItemResponse;
import com.taobao.api.ApiException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/7/11
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class TaobaoGoodsAction extends BaseAction {
    private static Log  logger = LogFactory.getLog(TaobaoGoodsAction.class);
    private String docid;
    private String bcode;
    private InputStream inputStream;
    private List onsales;
    private Integer total;
    private boolean hasTopSession;
    private List<Item> items = new ArrayList<Item>();
    private List<QrImage> goods = new ArrayList<QrImage>();
    private QrImgService qrImgService;
    private DocService docService;


    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public boolean isHasTopSession() {
        return hasTopSession;
    }

    public void setHasTopSession(boolean hasTopSession) {
        this.hasTopSession = hasTopSession;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setDocService(DocService docService) {
        this.docService = docService;
    }

    public void setQrImgService(QrImgService qrImgService) {
        this.qrImgService = qrImgService;
    }

    public List<QrImage> getGoods() {
        return goods;
    }

    public void setGoods(List<QrImage> goods) {
        this.goods = goods;
    }


    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List getOnsales() {
        return onsales;
    }

    public void setOnsales(List onsales) {
        this.onsales = onsales;
    }

    public String getUserOnsale(){
        logger.debug("method getUserOnsale begin");
        HttpServletRequest request = getRequest();
        try{
            if(getTopSession() != null){
                logger.debug("top session is null");
                String pageIndexName  =   new  org.displaytag.util.ParamEncoder("onsales").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
                Long  pageIndex  =  StringUtils.isBlank(request.getParameter(pageIndexName)) ? 0 :(Long.valueOf(request.getParameter(pageIndexName))  -   1 );
                ItemResponse res = qrImgService.getOnsale(getTopSession(),getUser().getId(),pageIndex+1,10l);
                onsales = res.getItems();
                total = res.getTotal().intValue();
                hasTopSession = true;
            }else{
                total = 0;
                hasTopSession = false;
            }
        }catch (Exception e){
            throw new RuntimeException("connection with tao error!");
        }
        logger.debug("method getUserOnsale end");
        return SUCCESS;
    }

    public String getGencode(){
       logger.debug("method getGencode begin");
       HttpServletRequest request = getRequest();

       Long userId = getUser().getId();
       String pageIndexName  =   new  org.displaytag.util.ParamEncoder("goods").encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

       Integer  pageIndex  =  StringUtils.isBlank(request.getParameter(pageIndexName)) ? 0 :(Integer.valueOf(request.getParameter(pageIndexName))  -   1 );
       logger.debug("page index ="+ pageIndex);

       goods = qrImgService.gtCodes(userId,pageIndex*10,10);
       total = qrImgService.getCodeTotal(userId).intValue();
       logger.debug("total record size ="+total);
       logger.debug("method getGencode end");
       return SUCCESS;
    }


    public String genQrcode() throws IOException, ApiException, WriterException, RepositoryException {
        logger.debug("method genQrcode begin");
        if(!items.isEmpty()){
            goods = qrImgService.generateTaobaoCode(items, getUser());
        }
        logger.debug("method genQrcode begin");
        return SUCCESS;
    }


//    public String

    public InputStream getInputStream() throws Exception{
        return  inputStream;
    }

    public String download() throws RepositoryException, IOException {
        logger.debug("method download begin docid = "+docid);
        inputStream =  docService.getdownloadFile(docid);
        logger.debug("method download end");
        return SUCCESS;
    }

    public String getDownloadFileName(){
        return docid;
    }
}
