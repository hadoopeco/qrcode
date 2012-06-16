package com.pupuly.service;

import com.pupuly.service.jcr.SessionFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/14/11
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */
public interface DocService {
    public String saveNode(String path, InputStream in) throws RepositoryException, IOException;


    public InputStream getdownloadFile(String uuid) throws RepositoryException, IOException;

}
