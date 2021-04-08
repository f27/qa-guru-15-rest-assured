package tests;

import api.model.UsersData;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Story("Обучение REST-assured")
@Tag("api")
@DisplayName("API тесты для https://reqres.in/")
public class ApiTests extends TestBase {

    @Test
    @DisplayName("Проверка, что у всех пользователей не пустой id")
    void accountsTest() {
        int i = 1;
        UsersData[] usersDataList;
        while ((usersDataList = apiSteps.getUsers(i)).length > 0) {
            for (UsersData userData : usersDataList) {
                apiSteps.checkUser(userData.getId());
            }
            i++;
        }
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    void successfulRegistrationTest() {
        Map<String, String> postData = new HashMap<String, String>() {{
            put("email", "eve.holt@reqres.in");
            put("password", "pistol");
        }};
        apiSteps
                .register(postData)
                .then()
                .log().body()
                .statusCode(200)
                .body("id", is(notNullValue()))
                .body("token", is(notNullValue()));
    }

    @Test
    @DisplayName("Проверка регистрации без пароля")
    void registrationWithoutPasswordTest() {
        Map<String, String> postData = new HashMap<String, String>() {{
            put("email", "eve.holt@reqres.in");
        }};
        apiSteps.register(postData)
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    @DisplayName("Проверка успешного входа")
    void successfulLoginTest() {
        Map<String, String> postData = new HashMap<String, String>() {{
            put("email", "eve.holt@reqres.in");
            put("password", "pistol");
        }};
        apiSteps.login(postData)
                .then()
                .statusCode(200)
                .body("token", is(notNullValue()));
    }

    @Test
    @DisplayName("Проверка входа без пароля")
    void unsuccessfulLoginTest() {
        Map<String, String> postData = new HashMap<String, String>() {{
            put("email", "eve.holt@reqres.in");
        }};
        apiSteps.login(postData)
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    @DisplayName("Проверка удаления пользователя")
    void deleteUserTest() {
        apiSteps.deleteUser(1)
                .then()
                .statusCode(204);
    }

}
