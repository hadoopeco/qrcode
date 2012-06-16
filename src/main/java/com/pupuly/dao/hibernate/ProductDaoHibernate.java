package com.pupuly.dao.hibernate;

import com.pupuly.dao.ProductDao;

import com.pupuly.model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/25/11
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("productDao")
public class ProductDaoHibernate extends GenericDaoHibernate<Product,Long> implements ProductDao{

    public ProductDaoHibernate() {
        super(Product.class);
    }

    public List<Product> getUserProductsPagging(final Long userId, final int page, final int size){
        return getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                String queryStr = "from Product p where p.user.id = "+userId+" order by p.addDt desc";
                Query query = session.createQuery(queryStr);
                query.setFirstResult(page);
                query.setMaxResults(size);
                return query.list();
            }
        });
    }

    public Long getUserProductsTotal(Long userId){
        List list = getHibernateTemplate().find("select count(*) from Product p where p.user.id = ?",userId);
        return (Long)list.get(0);
    }


    public List<Product> getUserProductByids(Long userId, String ids) {
        List list = getHibernateTemplate().find("from Product p where p.user.id = ? and p.id in ("+ids+") order by p.addDt desc", userId);
        return list;
    }
}
