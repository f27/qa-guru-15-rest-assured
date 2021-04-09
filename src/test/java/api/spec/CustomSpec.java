package api.spec;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static filter.LogFilter.filters;

public class CustomSpec {
    public RequestSpecification customSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setContentType(ContentType.JSON)
            .addFilter(filters().withCustomTemplates())
            .build();

    public static CustomSpec spec() {
        return new CustomSpec();
    }
}
