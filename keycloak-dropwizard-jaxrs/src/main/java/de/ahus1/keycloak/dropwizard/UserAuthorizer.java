package de.ahus1.keycloak.dropwizard;

import io.dropwizard.auth.Authorizer;

import javax.ws.rs.ForbiddenException;

public class UserAuthorizer implements Authorizer<User> {

    private final String resource;

    public UserAuthorizer(String resource) {
        this.resource = resource;
    }

    @Override
    public boolean authorize(User user, String role) {
        try {
            user.checkUserInRole(role, resource);
            return true;
        } catch (ForbiddenException e) {
            return false;
        }
    }
}
