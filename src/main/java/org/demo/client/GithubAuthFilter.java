package org.demo.client;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Priority(Priorities.AUTHENTICATION)
public class GithubAuthFilter implements ClientRequestFilter {

    @Inject
    @ConfigProperty(name = "github.token")
    String GITHUB_TOKEN;

    @Override
    public void filter(ClientRequestContext requestContext) {
        if (!GITHUB_TOKEN.isBlank()) {
            requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + GITHUB_TOKEN);
        }
    }
}