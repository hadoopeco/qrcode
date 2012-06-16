package com.pupuly.service.impl;

import com.pupuly.dao.ProductDao;
import com.pupuly.model.Product;
import com.pupuly.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/22/11
 * Time: 6:23 PM
 * To change this template use File | Settings | File Templates.
 */
@Service("productService")
public class ProductServiceImpl extends GenericManagerImpl<Product,Long> implements ProductService{


    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.dao = productDao;
        this.productDao = productDao;
    }

    public List<Product> getUserProductsPagging(Long userId,int page, int size){
       return  productDao.getUserProductsPagging(userId,page,size);
    }

    public Long getUserProductsTotal(Long userId){
        return productDao.getUserProductsTotal(userId);
    }

    public List<Product> getUserProductByids(Long userId, String ids){
        return productDao.getUserProductByids(userId, ids);
    }
}
