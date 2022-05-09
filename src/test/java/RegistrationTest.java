import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import com.codeborne.selenide.WebDriverRunner;

import static io.restassured.RestAssured.delete;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationTest {

    ConstructorPage constructorPage;
    AuthorizationPage authorizationPage = Selenide.page(AuthorizationPage.class);
    RegistrationPage registrationPage;
    String token;
    boolean del;

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void registrationHappyTest() {
        del = true;
        constructorPage = open(Base.BASE_URL, ConstructorPage.class);
        constructorPage.getPersonalAccountButton().click();
        authorizationPage.getRegisterLink().click();
        registrationPage = page(RegistrationPage.class);
        registrationPage.registration();

        authorizationPage.getRegisterLink().shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(authorizationPage.url));
    }

    @Test
    @DisplayName("Ошибка регистрации с невалидным паролем")
    public void registrationWrongPasswordErrorTest() {
        del = false;
        constructorPage = open(Base.BASE_URL, ConstructorPage.class);
        constructorPage.getPersonalAccountButton().click();
        authorizationPage.getRegisterLink().click();
        registrationPage = page(RegistrationPage.class);
        registrationPage.registration("pass");

        registrationPage.getIncorrectPasswordWarning().shouldBe(Condition.visible);
    }

    @After
    public void tearDown() {
        if (del) {
            token = UserOperations.authorizationBack(registrationPage.emailValue, registrationPage.passwordValue);
            delete(token);
        }
    }
}