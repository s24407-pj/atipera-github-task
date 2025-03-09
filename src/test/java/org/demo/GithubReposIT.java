package org.demo;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@QuarkusTest
public class GithubReposIT {
    @Test
    public void testGetRepositories() {

        RestAssured.given()
                .when().get("/api/v1/s24407-pj/repositories")
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(0)))
                .body("[0].name", notNullValue())
                .body("[0].owner", notNullValue())
                .body("[0].branches", hasSize(greaterThan(0)));

}
}

