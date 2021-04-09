package api.steps;

import api.model.Login;
import api.model.Register;
import api.model.UserData;
import api.model.UsersData;
import io.qameta.allure.Step;
import java.util.Map;

import static api.spec.CustomSpec.spec;
import static io.restassured.RestAssured.given;

public class ApiSteps {

    @Step("Получаем список пользователей со страницы {page}")
    public UsersData[] getUsers(int page) {
        return given(spec().customSpec)
                .get("/api/users?page=" + page)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject("data", UsersData[].class);
    }

    @Step("Проверяем пользователя с id {id}")
    public UserData checkUser(String id) {
        return given(spec().customSpec)
                .get("/api/users/" + id)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject("data", UserData.class);
    }

    @Step("Регистрация с данными {data}")
    public Register register(Map<String, String> data, int statusCode) {
        return given(spec().customSpec)
                .body(data)
                .post("/api/register")
                .then()
                .statusCode(statusCode)
                .extract()
                .as(Register.class);
    }

    @Step("Вход с данными {data}")
    public Login login(Map<String, String> data, int statusCode) {
        return given(spec().customSpec)
                .body(data)
                .post("/api/login")
                .then()
                .statusCode(statusCode)
                .extract()
                .as(Login.class);
    }

    @Step("Удаление пользователя с id {id}")
    public void deleteUser(int id) {
        given(spec().customSpec)
                .delete("/api/users/" + id)
                .then()
                .statusCode(204);
    }
}
