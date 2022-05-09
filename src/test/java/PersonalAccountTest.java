import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.delete;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonalAccountTest {

    ConstructorPage constructorPage;
    AuthorizationPage authorizationPage = page(AuthorizationPage.class);
    RegistrationPage registrationPage;
    PersonalAccountPage personalAccountPage = page(PersonalAccountPage.class);
    String token;
    boolean logout;

    @Before
    @Step("Регистрация пользователя")
    public void registration() {
        constructorPage = open(Base.BASE_URL, ConstructorPage.class);
        constructorPage.getPersonalAccountButton().click();
        authorizationPage.getRegisterLink().click();
        registrationPage = page(RegistrationPage.class);
        registrationPage.registration();
        authorizationPage.getEntranceHeader().shouldBe(Condition.visible);
        authorizationPage.authorization(registrationPage.emailValue, registrationPage.passwordValue);
    }


    @Test
    @DisplayName("Переход в личный кабинет")
    public void transferToPersonalAccountSuccessTest() {
        logout = true;
        constructorPage.getPersonalAccountButton().click();

        personalAccountPage.getProfileButton().shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(personalAccountPage.url));
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void switchingFromPersonalAccountToConstructorViaConstructorButtonSuccessTest() {
        logout = true;
        constructorPage.getPersonalAccountButton().click();
        personalAccountPage.getConstructorButton().click();

        constructorPage.getAssembleBurgerHeader().shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(constructorPage.url));
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void switchingFromPersonalAccountToConstructorViaLogoSuccessTest() {
        logout = true;
        constructorPage.getPersonalAccountButton().click();
        personalAccountPage.getStellarBurgersLogo().click();

        constructorPage.getAssembleBurgerHeader().shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(constructorPage.url));
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void logoutFromAccountSuccessTest() {
        logout = false;
        constructorPage.getPersonalAccountButton().click();
        personalAccountPage.getLogoutButton().click();

        authorizationPage.getEntranceHeader().shouldBe(Condition.visible);
        assertThat(WebDriverRunner.getWebDriver().getCurrentUrl(), equalTo(authorizationPage.url));
    }


    @After
    @Step("Выход из системы")
    public void down() {
        if (logout) {
            authorizationPage.getConstructorButton().click();
            constructorPage.getPersonalAccountButton().click();
            personalAccountPage.getLogoutButton().click();
            authorizationPage.getConstructorButton().click();
        }

        token = UserOperations.authorizationBack(registrationPage.emailValue, registrationPage.passwordValue);
        delete(token);
    }
}