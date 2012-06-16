package com.pupuly.dao;

import com.pupuly.model.QrQueryLog;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/19/11
 * Time: 8:48 PM
 * To change this template use File | Settings | File Templates.
 */
public interface QrLogDao extends GenericDao<QrQueryLog,Long>{

    public List getUserQueryLog(final Long userId,final int page, final int max);

    public Integer getTotalUserQueryLogs(long userId);
}
