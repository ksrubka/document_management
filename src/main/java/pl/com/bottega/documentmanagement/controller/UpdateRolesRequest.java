package pl.com.bottega.documentmanagement.controller;

import java.util.Set;

public class UpdateRolesRequest {

    private Set<String> roles;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
