package com.pupuly.service;

import com.pupuly.model.QrQueryLog;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 9/19/11
 * Time: 8:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface QrLogService {

    public QrQueryLog findById(Long id);

    public QrQueryLog create(QrQueryLog log);

    public List<QrQueryLog> findAll();

    public List getUserQueryLog(final Long userId, final int page, final int max);

    public Integer getTotalUserQueryLogs(long userId);
}
