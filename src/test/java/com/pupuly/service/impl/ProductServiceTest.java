package com.pupuly.service.impl;

import com.pupuly.service.ProductService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/29/11
 * Time: 11:32 PM
 * To change this template use File | Settings | File Templates.
 */
@ContextConfiguration(locations = {"classpath:/applicationContext-service.xml","classpath:/applicationContext-dao.xml","classpath:/applicationContext-resources.xml"})
public class ProductServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ProductService productService;

    @Test
    public void test(){

        productService.getUserProductByids(1l,"1,2,3");
    }
}
