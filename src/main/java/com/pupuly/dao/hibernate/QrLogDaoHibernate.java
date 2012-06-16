package com.pupuly.dao.hibernate;

import com.pupuly.dao.QrLogDao;
import com.pupuly.model.QrQueryLog;
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
 * Date: 9/19/11
 * Time: 8:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("qrLogDao")
public class QrLogDaoHibernate extends GenericDaoHibernate<QrQueryLog,Long> implements QrLogDao{

    public QrLogDaoHibernate() {
        super(QrQueryLog.class);
    }

    public List getUserQueryLog(final Long userId,final int page, final int max){
        return getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                String queryStr = "from QrQueryLog ql where ql.user.id ="+userId +" order by ql.queryDate desc";
                Query query =  session.createQuery(queryStr);
                query.setFirstResult(page);
                query.setMaxResults(max);
                return query.list();
            }
        });
    }


    public Integer getTotalUserQueryLogs(long userId) {
        List list = getHibernateTemplate().find("select count(*) from QrQueryLog ql where ql.user.id = ?",userId);
        return ((Long)list.get(0)).intValue();
    }
}
