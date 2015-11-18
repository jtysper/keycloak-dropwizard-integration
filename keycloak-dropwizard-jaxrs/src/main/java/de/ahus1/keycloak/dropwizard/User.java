package de.ahus1.keycloak.dropwizard;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ForbiddenException;

/**
 * This is a default implementation for a user. Consider extending this class or AbstractUser
 * with any needed wrapper around AccessToken and IDToken.
 */
public class User extends AbstractUser {

    public User(KeycloakSecurityContext securityContext, HttpServletRequest request) {
        super(request, securityContext);
    }

    public void checkUserInRole(String role, String resource) {
        if(!access(resource).isUserInRole(role)) {
            throw new ForbiddenException();
        }
    }

    private AccessToken.Access access(String resource) {
        if(resource == null) {
            throw new ForbiddenException();
        }
        return securityContext.getToken().getResourceAccess(resource);
    }

    public String getName() {
        return securityContext.getToken().getName();
    }

}
