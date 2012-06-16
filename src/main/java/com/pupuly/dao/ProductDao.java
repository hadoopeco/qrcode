package com.pupuly.dao;


import com.pupuly.model.Product;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/25/11
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ProductDao extends GenericDao<Product,Long>{

    public List<Product> getUserProductsPagging(final Long userId, final int page, int size);

    public Long getUserProductsTotal(Long userId);

    public List<Product> getUserProductByids(Long userId, String ids);
}
