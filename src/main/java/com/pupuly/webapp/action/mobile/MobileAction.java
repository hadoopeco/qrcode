package com.pupuly.webapp.action.mobile;

import com.pupuly.model.QrImage;
import com.pupuly.model.QrQueryLog;
import com.pupuly.service.QrImgService;
import com.pupuly.service.QrLogService;
import com.pupuly.webapp.action.BaseAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/30/11
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class MobileAction extends BaseAction{
    private static Log logger = LogFactory.getLog(MobileAction.class);

    private QrImgService qrImgService;

    private QrLogService qrLogService;

    private String code;

    private String rdUrl;

    public void setQrImgService(QrImgService qrImgService) {
        this.qrImgService = qrImgService;
    }

    public void setQrLogService(QrLogService qrLogService) {
        this.qrLogService = qrLogService;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRdUrl() {
        return rdUrl;
    }

    public void setRdUrl(String rdUrl) {
        this.rdUrl = rdUrl;
    }

    public String search(){
        logger.debug("MobileAction.search begin");
        logger.debug("MobileAction.search code = "+code);
        QrImage img =  qrImgService.getGoodsByCode(code);
        rdUrl = img.getGoodsUrl();
        QrQueryLog log = new QrQueryLog();
        log.setQrImg(img);
        log.setQueryDate(new Date());
        log.setId(new Date().getTime());
        try{
            log.setUser(getUser());
        }catch (Exception e){}
        log.setUuid("test");
        qrLogService.create(log);
        logger.debug("MobileAction.search");
        return SUCCESS;
    }
}
