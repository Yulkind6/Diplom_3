import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {

    //локатор поля ввода "Имя"
    @FindBy(how = How.XPATH, using = "//div/label[text()='Имя']/following::input[1]")
    private SelenideElement nameInput;

    //локатор поля ввода "Email"
    @FindBy(how = How.XPATH, using = "//div/label[text()='Email']/following::input[1]")
    private SelenideElement emailInput;

    //локатор поля ввода "Пароль"
    @FindBy(how = How.XPATH, using = "//div/label[text()='Пароль']/following::input[1]")
    private SelenideElement passwordInput;

    //локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = "//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']")
    private SelenideElement registerButton;

    //локатор предупреждения об ошибке "Некорректный пароль"
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordWarning;

    //локатор ссылки "Войти"
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement loginLink;


    public String nameValue;
    public String emailValue;
    public String passwordValue;

    @Step("Регистрация пользователя")
    public void registration() {
        emailValue = RandomStringUtils.randomAlphabetic(10) + UserOperations.EMAIL_POSTFIX;
        passwordValue = RandomStringUtils.randomAlphabetic(10);
        nameValue = RandomStringUtils.randomAlphabetic(10);

        nameInput.setValue(nameValue);
        emailInput.setValue(emailValue);
        passwordInput.setValue(passwordValue);
        registerButton.click();
    }

    @Step("Регистрация пользователя с невалидным паролем")
    public void registration(String passwordValue) {
        emailValue = RandomStringUtils.randomAlphabetic(10) + UserOperations.EMAIL_POSTFIX;
        nameValue = RandomStringUtils.randomAlphabetic(10);

        nameInput.setValue(nameValue);
        emailInput.setValue(emailValue);
        passwordInput.setValue(passwordValue);
        registerButton.click();
    }

    public SelenideElement getLoginLink() {
        return loginLink;
    }

    public SelenideElement getIncorrectPasswordWarning() {
        return incorrectPasswordWarning;
    }
}