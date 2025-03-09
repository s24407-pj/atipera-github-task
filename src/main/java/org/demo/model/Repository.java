package org.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Repository {
    private String name;

    private String ownerLogin;

    @JsonProperty("fork")
    private boolean isFork;

    @JsonProperty("owner")
    private void unpackNested(Map<String, Object> owner) {
        this.ownerLogin = (String) owner.get("login");

    }

    public String getName() {
        return name;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public boolean isFork() {
        return isFork;
    }
}
