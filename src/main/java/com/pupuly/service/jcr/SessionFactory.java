package com.pupuly.service.jcr;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/13/11
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SessionFactory {

    public Session getSession() throws RepositoryException;
}
