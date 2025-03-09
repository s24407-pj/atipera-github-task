package org.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Branch {
    private String name;
    private String lastCommitSha;

    @JsonProperty("commit")
    private void unpackNested(Map<String, Object> commit) {
        this.lastCommitSha = (String) commit.get("sha");
    }

    public String getName() {
        return name;
    }

    public String getLastCommitSha() {
        return lastCommitSha;
    }
}
