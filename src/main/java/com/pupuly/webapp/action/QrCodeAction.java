package com.pupuly.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import com.pupuly.service.QrLogService;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/21/11
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class QrCodeAction extends ActionSupport{

    private List qrLogs;

    public void setQrLogs(List qrLogs) {
        this.qrLogs = qrLogs;
    }

    private QrLogService qrLogService;

    public void setQrLogService(QrLogService qrLogService) {
        this.qrLogService = qrLogService;
    }



    public String viewLog(){
        qrLogs = qrLogService.findAll();
        return SUCCESS;
    }
}
