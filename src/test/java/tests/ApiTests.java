package tests;

import api.model.UsersData;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Story("Обучение REST-assured")
@Tag("api")
public class ApiTests extends TestBase {
    @Test
    @DisplayName("Проверка, что в запросах к списку пользователей id не пустой")
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

}
