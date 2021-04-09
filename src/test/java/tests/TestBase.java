package tests;

import api.steps.ApiSteps;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    static ApiSteps apiSteps;

    @BeforeAll
    static void init() {
        apiSteps = new ApiSteps();
    }
}
