package com.pupuly.service.impl;

import com.pupuly.service.DocService;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.persistence.criteria.Root;
import java.io.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jackrabbit.value.BinaryImpl;
import org.springframework.stereotype.Service;
import org.springmodules.jcr.JcrCallback;
import org.springmodules.jcr.JcrConstants;
import org.springmodules.jcr.JcrTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/14/11
 * Time: 12:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class DocServiceImpl implements DocService {
    private static Log logger = LogFactory.getLog(DocServiceImpl.class);
    private JcrTemplate jcrTemplate;

    public JcrTemplate getJcrTemplate() {
        return jcrTemplate;
    }

    public void setJcrTemplate(JcrTemplate jcrTemplate) {
        this.jcrTemplate = jcrTemplate;
    }

    public String saveNode(final String docpath, final InputStream in) throws RepositoryException, IOException {
        return (String)jcrTemplate.execute(new JcrCallback() {
            public Object doInJcr(Session session) throws IOException, RepositoryException {
                Node root = session.getRootNode();
                String path[] = docpath.split("/");
                Node node = root;
                for(String p:path){
                    if(!node.hasNode(p)){
                        node = node.addNode(p);
                    }else{
                        node = node.getNode(p);
                    }
                }
                node.setProperty("image", new BinaryImpl(in));
                session.save();
                return node.getIdentifier();
            }
        });
    }


//    public Node getNode(String uuid) throws RepositoryException {
//       logger.debug("method getNode begin");
//       logger.debug("jcr session begin uuid=" + uuid);
//       Node node = jcrTemplate.getNodeByUUID(uuid);
//       logger.debug("method getNode end");
//       return  node;
//    }

    public InputStream getdownloadFile(final String uuid) throws RepositoryException, IOException {
        logger.debug("method getdownload file end");
        logger.debug("jcr session login");
        InputStream ins = (InputStream)jcrTemplate.execute(new JcrCallback() {
            public Object doInJcr(Session session) throws IOException, RepositoryException {
                Node node = session.getNodeByIdentifier(uuid);
                Binary binary = node.getProperty("image").getBinary();
                return binary.getStream();
            }
        });

        logger.debug("method getDownload file end");
        return ins;
    }

    private Node getNode(Node node,String docPath) throws RepositoryException {
        logger.debug("method getNode begin");
        String path[] = docPath.split("/");
        for(String p:path){
            if(!node.hasNode(p)){
               node = node.addNode(p);
            }else {
               node = node.getNode(p);
            }
        }
        logger.debug("method getNode end");
        return node;
    }
}

