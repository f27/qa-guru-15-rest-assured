package api.spec;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static filter.LogFilter.filters;
import static io.restassured.RestAssured.given;

public class CustomSpec {
    private final RequestSpecification request = given()
            .baseUri("https://reqres.in")
            .contentType(ContentType.JSON)
            .filter(new AllureRestAssured())
            .log().uri()
            .when();

    public static CustomSpec spec() {
        return new CustomSpec();
    }

    public RequestSpecification request() {
        return request;
    }
}
