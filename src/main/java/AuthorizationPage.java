import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AuthorizationPage {

    //локатор кнопки Конструктор
    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    //локатор заголовка Вход
    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement entranceHeader;

    //локатор поля ввода Email
    @FindBy(how = How.XPATH, using = "//div/label[text()='Email']/following::input[1]")
    private SelenideElement emailInput;

    //локатор поля ввода Пароль
    @FindBy(how = How.XPATH, using = "//div/label[text()='Пароль']/following::input[1]")
    private SelenideElement passwordInput;

    //локатор кнопки Войти
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement loginButton;

    //локатор линка Зарегистрироваться
    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private SelenideElement registerLink;

    //локатор линка Восстановить пароль
    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private SelenideElement recoverPasswordLink;


    public final String url = "https://stellarburgers.nomoreparties.site/login";

    public AuthorizationPage() {
    }

    //Авторизация
    public void authorization(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        loginButton.click();
    }

    public SelenideElement getRegisterLink() {
        return registerLink;
    }

    public SelenideElement getEntranceHeader() {
        return entranceHeader;
    }

    public SelenideElement getConstructorButton() {
        return constructorButton;
    }

    public SelenideElement getRecoverPasswordLink() {
        return recoverPasswordLink;
    }
}