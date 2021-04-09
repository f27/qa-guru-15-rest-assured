package tests;

import api.model.Register;
import api.model.UsersData;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
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
                assertThat(apiSteps.checkUser(userData.getId()).getId(), is(notNullValue()));
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
        Register register = apiSteps.register(postData, 200);

        assertThat(register.getId(), is(notNullValue()));
        assertThat(register.getToken(), is(notNullValue()));
    }

    @Test
    @DisplayName("Проверка регистрации без пароля")
    void registrationWithoutPasswordTest() {
        Map<String, String> postData = new HashMap<String, String>() {{
            put("email", "eve.holt@reqres.in");
        }};

        assertThat(apiSteps.register(postData, 400).getError(), is("Missing password"));
    }

    @Test
    @DisplayName("Проверка успешного входа")
    void successfulLoginTest() {
        Map<String, String> postData = new HashMap<String, String>() {{
            put("email", "eve.holt@reqres.in");
            put("password", "pistol");
        }};

        assertThat(apiSteps.login(postData, 200).getToken(), is(notNullValue()));
    }

    @Test
    @DisplayName("Проверка входа без пароля")
    void unsuccessfulLoginTest() {
        Map<String, String> postData = new HashMap<String, String>() {{
            put("email", "eve.holt@reqres.in");
        }};

        assertThat(apiSteps.login(postData, 400).getError(), is("Missing password"));
    }

    @Test
    @DisplayName("Проверка удаления пользователя")
    void deleteUserTest() {
        apiSteps.deleteUser(1);
    }

}
