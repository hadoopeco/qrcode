package com.pupuly.service;

import com.google.zxing.WriterException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.jcr.RepositoryException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/15/11
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = {"classpath:/applicationContext-service.xml","classpath:/applicationContext-dao.xml","classpath:/applicationContext-resources.xml"})
public class GoodsTestCase extends AbstractJUnit4SpringContextTests {

    @Autowired
    private QrImgService qrImgService;

    public void setQrImgService(QrImgService qrImgService) {
        this.qrImgService = qrImgService;
    }

    @Test
    public void testGetcode() throws RepositoryException, IOException, WriterException {
        String s = qrImgService.generateCode("testsetetsst","taobao/test/idasda");
        System.out.println("GoodsTestCase.testGetcode  code ="+s);
    }

    @Test
    public void testGetCodeTotal(){
         Long s = qrImgService.getCodeTotal(4l);
        System.out.println("GoodsTestCase.testGetCodeTotal :" +s );
    }

    @Test
    public void testGetCodeEmpty(){
        Long s = qrImgService.getCodeTotal(-1l);
        System.out.println("GoodsTestCase.testGetCodeEmpty " +s);
    }

}
