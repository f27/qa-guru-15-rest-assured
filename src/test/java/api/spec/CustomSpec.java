package api.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static filter.LogFilter.filters;
import static io.restassured.RestAssured.given;

public class CustomSpec {
    public static RequestSpecification customSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setContentType(ContentType.JSON)
            .build();

    private final RequestSpecification request = given(customSpec)
            .filter(filters().withCustomTemplates())
            .log().uri()
            .when();

    public static CustomSpec spec() {
        return new CustomSpec();
    }

    public RequestSpecification request() {
        return request;
    }
}
