package com.pupuly.service;


import com.pupuly.model.Product;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/22/11
 * Time: 6:17 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ProductService extends GenericManager<Product,Long>{

    public List<Product> getUserProductsPagging(Long userId,int page, int size);

    public Long getUserProductsTotal(Long userId);

    public List<Product> getUserProductByids(Long userId, String ids);


}
