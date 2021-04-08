package tests;

import api.steps.ApiSteps;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    static ApiSteps apiSteps;

    @BeforeAll
    static void init() {
        apiSteps = new ApiSteps();
        RestAssured.filters(new AllureRestAssured());
        RestAssured.baseURI = "https://reqres.in";
    }
}
