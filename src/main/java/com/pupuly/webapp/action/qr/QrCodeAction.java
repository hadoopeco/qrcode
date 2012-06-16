package com.pupuly.webapp.action.qr;

import com.pupuly.model.QrQueryLog;
import com.pupuly.service.QrLogService;
import com.pupuly.webapp.action.BaseAction;
import com.sun.deploy.net.HttpRequest;
import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/28/11
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class QrCodeAction extends BaseAction{

    private Integer total;

    private List<QrQueryLog> querys;

    private QrLogService qrLogService;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<QrQueryLog> getQuerys() {
        return querys;
    }

    public void setQuerys(List<QrQueryLog> querys) {
        this.querys = querys;
    }

    public void setQrLogService(QrLogService qrLogService) {
        this.qrLogService = qrLogService;
    }

    public String list(){
        HttpServletRequest request = getRequest();
        Long userId = getUser().getId();
        String pageIndexName = new ParamEncoder("querys").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
        Long  pageIndex  =  StringUtils.isBlank(request.getParameter(pageIndexName)) ? 0 :(Long.valueOf(request.getParameter(pageIndexName))  -   1 );
        querys =  qrLogService.getUserQueryLog(userId,pageIndex.intValue()*10,10);
        total = qrLogService.getTotalUserQueryLogs(userId);
        return SUCCESS;
    }
}
