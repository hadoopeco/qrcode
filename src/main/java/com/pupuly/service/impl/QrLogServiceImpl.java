package com.pupuly.service.impl;

import com.pupuly.dao.QrLogDao;
import com.pupuly.model.QrQueryLog;
import com.pupuly.service.QrLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/19/11
 * Time: 8:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("qrLogService")
public class QrLogServiceImpl extends GenericManagerImpl<QrQueryLog,Long> implements QrLogService{


    private QrLogDao qrLogDao;


    @Autowired
    public void setQrLogDao(QrLogDao qrLogDao) {
        this.dao = qrLogDao;
        this.qrLogDao = qrLogDao;

    }

    public QrQueryLog findById(Long id) {
        return get(id);
    }

    public QrQueryLog create(QrQueryLog log){
        return save(log);
    }

    public List<QrQueryLog> findAll(){
        return super.getAll();
    }

    public List getUserQueryLog(Long userId,int page,int max){
        return qrLogDao.getUserQueryLog(userId,page,max);
    }

    public Integer getTotalUserQueryLogs(long userId){
        return qrLogDao.getTotalUserQueryLogs(userId);
    }
}
