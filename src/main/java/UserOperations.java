import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserOperations {

    public static final String EMAIL_POSTFIX = "@yandex.ru";

    public Map<String, String> register() {
        String email = RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX;
        String password = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(10);

        Map<String, String> inputDataMap = new HashMap<>();
        inputDataMap.put("email", email);
        inputDataMap.put("password", password);
        inputDataMap.put("name", name);

        UserRegisterResponse response = given()
                .spec(Base.getBaseSpec())
                .and()
                .body(inputDataMap)
                .when()
                .post("auth/register")
                .body()
                .as(UserRegisterResponse.class);

        Map<String, String> responseData = new HashMap<>();
        if (response != null) {
            responseData.put("email", response.getUser().getEmail());
            responseData.put("name", response.getUser().getName());
            responseData.put("password", password);

            Tokens.setAccessToken(response.getAccessToken().substring(7));
            Tokens.setRefreshToken(response.getRefreshToken());
        }
        return responseData;
    }

    public void delete() {
        if (Tokens.getAccessToken() == null) {
            return;
        }
        given()
                .spec(Base.getBaseSpec())
                .auth().oauth2(Tokens.getAccessToken())
                .when()
                .delete("auth/user")
                .then()
                .statusCode(202);
    }

    @Step("Авторизация пользователя на бэке")
    public static String authorizationBack(String email, String password) {
        Map<String, String> request = new HashMap<>();
        request.put("email", email);
        request.put("password", password);

        Response response = given()
                .spec(Base.getBaseSpec())
                .and()
                .log().body()
                .body(request)
                .when()
                .post("auth/login");

        response.then()
                .statusCode(200);

        return response.then().extract().path("accessToken");
    }

    @Step("Удаление пользователя")
    public static void delete(String token) {
        given()
                .spec(Base.getBaseSpec())
                .and()
                .header("Authorization", token)
                .when()
                .delete("auth/user")
                .then()
                .log().body()
                .statusCode(202);
    }

}
