package com.pupuly.service;

import com.pupuly.model.QrQueryLog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/19/11
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class QrLogServiceTestCase extends BaseManagerTestCase{
    @Autowired
    private QrLogService qrLogService;


    @Test
    public  void testGetById(){
        qrLogService.findById(11l);
    }

    @Test
    public void testGetAll(){
        List all = qrLogService.findAll();
        if(all!=null &&  !all.isEmpty()){
            QrQueryLog loget = null;
            for(Object obj : all){
                loget = (QrQueryLog) obj;
                System.out.println("QrLogServiceTestCase.testGetAll " +loget.getId());
            }
        }
    }

}
