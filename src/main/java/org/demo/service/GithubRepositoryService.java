package org.demo.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.demo.exception.ApiError;
import org.demo.exception.UserNotFoundException;
import org.demo.client.GithubClient;
import org.demo.model.RepositoryResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@ApplicationScoped
public class GithubRepositoryService {
    @Inject
    @RestClient
    GithubClient githubClient;

    public Uni<Response> getUserRepositories(String user) {
        return githubClient.getRepositories(user)
                // Filter out forked repositories
                .map(repos -> repos.stream()
                        .filter(repo -> !repo.isFork())
                        .collect(Collectors.toList()))
                // Get branches for each repository
                .flatMap(filteredRepos -> {
                    List<Uni<RepositoryResponse>> branchUnis = filteredRepos.stream()
                            .map(repo ->
                                    githubClient.getBranches(repo.getOwnerLogin(), repo.getName())
                                            .map(branches ->
                                                    new RepositoryResponse(
                                                            repo.getName(),
                                                            repo.getOwnerLogin(),
                                                            branches
                                                    )
                                            )
                            )
                            .collect(Collectors.toList());
                    return Uni.combine().all().unis(branchUnis).with(results -> results);
                })
                .map(responses -> Response.ok(responses).build())
                // Handle 404
                .onFailure(UserNotFoundException.class)
                .recoverWithItem(Response.status(NOT_FOUND)
                        .entity(new ApiError(
                                NOT_FOUND.getStatusCode(),
                                "User not found: " + user
                        ))
                        .build());
    }
}
