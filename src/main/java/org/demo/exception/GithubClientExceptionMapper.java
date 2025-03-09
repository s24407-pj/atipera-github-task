package org.demo.exception;


import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.client.ClientResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GithubClientExceptionMapper implements ClientResponseFilter {

    @Override
    public void filter(ClientRequestContext requestContext,
                       ClientResponseContext responseContext) {
        if (responseContext.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            throw new UserNotFoundException();
        }
    }
}
