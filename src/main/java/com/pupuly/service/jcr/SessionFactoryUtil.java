package com.pupuly.service.jcr;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/16/11
 * Time: 11:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionFactoryUtil {


    public static Session getSession(SessionFactory factory) throws RepositoryException {
        return factory.getSession();
    }

    public static void releasSession(Session session){
        session.logout();
    }


}
