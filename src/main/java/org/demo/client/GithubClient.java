package org.demo.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.demo.exception.GithubClientExceptionMapper;
import org.demo.model.Branch;
import org.demo.model.Repository;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "https://api.github.com")
@RegisterProvider(GithubAuthFilter.class)
@RegisterProvider(GithubClientExceptionMapper.class)
public interface GithubClient {

    @GET
    @Path("/users/{user}/repos")
    Uni<List<Repository>> getRepositories(
            @PathParam("user") String user
    );

    @GET
    @Path("/repos/{owner}/{repo}/branches")
    Uni<List<Branch>> getBranches(
            @PathParam("owner") String owner,
            @PathParam("repo") String repo);
}
