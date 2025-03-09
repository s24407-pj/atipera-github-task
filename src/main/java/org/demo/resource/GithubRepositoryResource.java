package org.demo.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.demo.service.GithubRepositoryService;

@Path("/api/v1")
@Produces(MediaType.APPLICATION_JSON)
public class GithubRepositoryResource {

    @Inject
    GithubRepositoryService githubRepositoryService;

    @GET
    @Path("/{user}/repositories")
    public Uni<Response> getRepositories(@PathParam("user") String user) {
        return githubRepositoryService.getUserRepositories(user);
    }
}
