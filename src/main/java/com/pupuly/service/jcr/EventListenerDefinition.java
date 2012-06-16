package com.pupuly.service.jcr;

import javax.jcr.observation.Event;
import javax.jcr.observation.EventListener;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/14/11
 * Time: 12:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class EventListenerDefinition {

    private EventListener listener;
    private int eventTypes = Event.NODE_ADDED | Event.NODE_REMOVED | Event.PROPERTY_ADDED | Event.PROPERTY_CHANGED | Event.PROPERTY_REMOVED;

    String absPath ="/";
    boolean isDeep = true;
    String[] uuid;
    String[] nodeTypeName;
    boolean noLocal = false;

    /**
     * @return Returns the absPath.
     */
    public String getAbsPath() {
        return absPath;
    }
    /**
     * @param absPath The absPath to set.
     */
    public void setAbsPath(String absPath) {
        this.absPath = absPath;
    }
    /**
     * @return Returns the eventTypes.
     */
    public int getEventTypes() {
        return eventTypes;
    }
    /**
     * @param eventTypes The eventTypes to set.
     */
    public void setEventTypes(int eventTypes) {
        this.eventTypes = eventTypes;
    }
    /**
     * @return Returns the isDeep.
     */
    public boolean isDeep() {
        return isDeep;
    }
    /**
     * @param isDeep The isDeep to set.
     */
    public void setDeep(boolean isDeep) {
        this.isDeep = isDeep;
    }
    /**
     * @return Returns the listener.
     */
    public EventListener getListener() {
        return listener;
    }
    /**
     * @param listener The listener to set.
     */
    public void setListener(EventListener listener) {
        this.listener = listener;
    }
    /**
     * @return Returns the nodeTypeName.
     */
    public String[] getNodeTypeName() {
        return nodeTypeName;
    }
    /**
     * @param nodeTypeName The nodeTypeName to set.
     */
    public void setNodeTypeName(String[] nodeTypeName) {
        this.nodeTypeName = nodeTypeName;
    }
    /**
     * @return Returns the noLocal.
     */
    public boolean isNoLocal() {
        return noLocal;
    }
    /**
     * @param noLocal The noLocal to set.
     */
    public void setNoLocal(boolean noLocal) {
        this.noLocal = noLocal;
    }
    /**
     * @return Returns the uuid.
     */
    public String[] getUuid() {
        return uuid;
    }
    /**
     * @param uuid The uuid to set.
     */
    public void setUuid(String[] uuid) {
        this.uuid = uuid;
    }

}
