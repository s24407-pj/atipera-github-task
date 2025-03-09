package org.demo.model;

import java.util.List;

public record RepositoryResponse(
        String name,
        String owner,
        List<Branch> branches
) {
}
