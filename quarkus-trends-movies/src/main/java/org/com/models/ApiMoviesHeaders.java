package org.com.models;

import jakarta.ws.rs.HeaderParam;

public class ApiMoviesHeaders {

    @HeaderParam("Authorization")
    private String authorization;


    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
