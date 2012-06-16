package com.pupuly.service.jcr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jackrabbit.commons.JcrUtils;
import org.springframework.beans.factory.InitializingBean;

import javax.jcr.*;
import javax.jcr.observation.ObservationManager;
import java.util.EventListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/13/11
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class JcrSessionFactory implements InitializingBean, SessionFactory {

    private static Log logger = LogFactory.getLog(JcrSessionFactory.class);

    private Repository repository;

    private Credentials credentials;

    private String workspace;



    private Properties namespaces;
    private EventListenerDefinition eventListeners[];

    public JcrSessionFactory(Repository repository, Credentials credentials, String workspace) {
        this.repository = repository;
        this.credentials = credentials;
        this.workspace = workspace;
    }


    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public Properties getNamespaces() {
        return namespaces;
    }

    public void setNamespaces(Properties namespaces) {
        this.namespaces = namespaces;
    }

    public Session getSession() throws RepositoryException {
        return repository.login(credentials);
    }

    public void afterPropertiesSet() throws Exception {
       if (getRepository() == null)
			throw new IllegalArgumentException("repository is required");
		if (eventListeners != null
				&& (eventListeners.length > 0)
				&& !supportsObservation(getRepository()))
			throw new IllegalArgumentException("repository "
//					+ getRepositoryInfo()
					+ " does NOT support Observation; remove Listener definitions");

		// register namespaces (if we have any)
		if (namespaces != null && !namespaces.isEmpty()) {
			if (logger.isDebugEnabled())
				logger.debug("registering custom namespaces " + namespaces);
			// get the session
			Session session = getSession();
			NamespaceRegistry registry = session.getWorkspace().getNamespaceRegistry();
			for (Iterator iter = namespaces.entrySet().iterator(); iter.hasNext();) {
				Map.Entry namespace = (Map.Entry) iter.next();
				registry.registerNamespace((String) namespace.getKey(), (String) namespace.getValue());
			}
		}
    }



    public static boolean supportsObservation(Repository repository) {
            return "true".equals(repository.getDescriptor(Repository.OPTION_OBSERVATION_SUPPORTED));
    }
}
