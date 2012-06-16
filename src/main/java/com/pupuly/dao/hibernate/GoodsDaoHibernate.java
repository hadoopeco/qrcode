package com.pupuly.dao.hibernate;

import com.pupuly.dao.GoodsDao;
import com.pupuly.model.QrImage;
import com.pupuly.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.From;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/7/11
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class GoodsDaoHibernate extends GenericDaoHibernate implements GoodsDao{



    public GoodsDaoHibernate() {
        super(QrImage.class);
    }

    public void saveAll(List goods){
        getHibernateTemplate().saveOrUpdateAll(goods);
    }

    public List getCodePaging(final Long userId,final int firstResult,final int maxResults){
        return  getHibernateTemplate().executeFind(
                new HibernateCallback<Object>() {
                    public Object doInHibernate(Session session) throws HibernateException, SQLException {
                        String   q   =   "from QrImage g where g.user.id = " + userId + " order by g.created_dt desc";
                        Query query   =   session.createQuery(q);
                        query.setFirstResult(firstResult);
                        query.setMaxResults(maxResults);
                        List   list   =   query.list();
                        return   list;
                    }
                }
        );
    }

    public List getCodeList(final Long userId){
        return  getHibernateTemplate().find("from QrImage g where g.user.id = " + userId);
    }

    public QrImage getCodeByNumiid(String numid){
         List goods = getHibernateTemplate().find("from QrImage g where g.numiid ="+numid);
         if(goods !=null && !goods.isEmpty()){
             return (QrImage)goods.get(0);
         }
         return null;
    }

    public Long getCodeTotal(Long userId){
        List  list = getHibernateTemplate().find(" select count(*) from QrImage g where g.user.id ="+userId);
        return (Long)list.get(0);
    }


}
