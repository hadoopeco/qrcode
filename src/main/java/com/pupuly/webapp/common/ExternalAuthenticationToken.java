package com.pupuly.webapp.common;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: wbmark
 * Date: 8/20/11
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExternalAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;
    private final int keyHash;

     public ExternalAuthenticationToken(String key, Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);

        if ((key == null) || ("".equals(key)) || (principal == null) || "".equals(principal)) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }

        this.keyHash = key.hashCode();
        this.principal = principal;
        setAuthenticated(true);
    }


     public Object getCredentials() {
        return "";
    }

    public int getKeyHash() {
        return this.keyHash;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        if (obj instanceof ExternalAuthenticationToken) {
            ExternalAuthenticationToken test = (ExternalAuthenticationToken) obj;

            if (this.getKeyHash() != test.getKeyHash()) {
                return false;
            }

            return true;
        }

        return false;
    }
}
