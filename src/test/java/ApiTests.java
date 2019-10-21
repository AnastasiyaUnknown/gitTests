import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.core.IsEqual.equalTo;

public class ApiTests {

    final String USER_NAME = "anastasiyaunknown";
    final String BASE_URL = "https://api.github.com/";
    final String VALID_OAUTH_TOKEN = "99301f6b0083a4f2e886fa2cf908f8947acb6225";
    final String INVALID_OAUTH_TOKEN = "99301f6b0083a4f2e886fa2cf908f8947acb6226";

    @BeforeEach
    public void setBASE_URL() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void isStatusCode200() {
        RestAssured.given()
                .when()
                .get("/users/" + USER_NAME)
                .then()
                .statusCode(200);
    }

    @Test
    public void checkCreatedDate() {
        RestAssured.given()
                .when()
                .get("/users/" + USER_NAME)
                .then()
                .body("created_at", equalTo("2019-10-19T16:49:43Z"));
    }

    @Test
    public void checkAuthStatusCodeWithValidToken() {
        RestAssured.given()
                .auth()
                .oauth2(VALID_OAUTH_TOKEN)
                .body("{}")
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("user.json"));
    }

    @Test
    public void changeNameWithValidToken() {
        RestAssured.given()
                .auth()
                .oauth2(VALID_OAUTH_TOKEN)
                .body("{\"name\": \"Anastasiya\"}")
                .when()
                .patch("/user")
                .then()
                .statusCode(200)
                .body("name", equalTo("Anastasiya"));;
    }

    @Test
    public void authenticateWithInvalidToken() {;
        RestAssured.given()
                .auth()
                .oauth2(INVALID_OAUTH_TOKEN)
                .body("{}")
                .when()
                .post("/user")
                .then()
                .statusCode(401);
    }
}