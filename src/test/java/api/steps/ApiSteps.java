package api.steps;

import api.model.UsersData;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static api.spec.CustomSpec.spec;
import static org.hamcrest.Matchers.notNullValue;

public class ApiSteps {

    @Step("Получаем список пользователей со страницы {page}")
    public UsersData[] getUsers(int page) {
        return spec().request()
                .get("/api/users?page=" + page)
                .jsonPath()
                .getObject("data", UsersData[].class);
    }

    @Step("Проверяем пользователя с id {id}")
    public void checkUser(String id) {
        spec().request()
                .get("/api/users/" + id)
                .then()
                .body("data.id", notNullValue());
    }

    @Step("Регистрация с данными {data}")
    public Response register(Map<String, String> data) {
        return spec().request()
                .body(data)
                .post("/api/register");
    }

    @Step("Вход с данными {data}")
    public Response login(Map<String,String> data) {
        return spec().request()
                .body(data)
                .post("/api/login");
    }

    @Step("Удаление пользователя с id {id}")
    public Response deleteUser(int id) {
        return spec().request()
                .delete("/api/users/"+id);
    }
}
