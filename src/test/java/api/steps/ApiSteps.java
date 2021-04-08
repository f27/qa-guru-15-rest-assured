package api.steps;

import api.model.UsersData;
import io.qameta.allure.Step;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.notNullValue;

public class ApiSteps {

    @Step("Получаем список пользователей со страницы {page}")
    public UsersData[] getUsers(int page) {
        return get("/api/users?page=" + page)
                .jsonPath()
                .getObject("data", UsersData[].class);
    }

    @Step("Проверяем пользователя с id {id}")
    public void checkUser(String id) {
        get("/api/users/" + id)
                .then()
                .body("data.id", notNullValue());
    }
}
