import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConstructorPage {

    //локатор кнопки "Личный кабинет"
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private SelenideElement personalAccountButton;

    //локатор заголовка "Соберите бургер"
    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement assembleBurgerHeader;

    //локатор вкладки "Булки"
    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    private SelenideElement burgersTab;

    //локатор вкладки "Соусы"
    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    private SelenideElement saucesTab;

    //локатор вкладки "Начинки"
    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    private SelenideElement fillingTab;

    //локатор заголовка "Булки"
    @FindBy(how = How.XPATH, using = "//h2[text()='Булки']")
    private SelenideElement burgersHeader;

    //локатор заголовка "Соусы"
    @FindBy(how = How.XPATH, using = "//h2[text()='Соусы']")
    private SelenideElement saucesHeader;

    //локатор заголовка "Начинки"
    @FindBy(how = How.XPATH, using = "//h2[text()='Начинки']")
    private SelenideElement fillingHeader;

    //локатор кнопки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement logInToAccountButton;


    public final String url = "https://stellarburgers.nomoreparties.site/";

    public SelenideElement getSaucesTab() {
        return saucesTab;
    }

    public SelenideElement getFillingTab() {
        return fillingTab;
    }

    public SelenideElement getSaucesHeader() {
        return saucesHeader;
    }

    public SelenideElement getFillingHeader() {
        return fillingHeader;
    }

    public SelenideElement getBurgersTab() {
        return burgersTab;
    }

    public SelenideElement getBurgersHeader() {
        return burgersHeader;
    }

    public SelenideElement getPersonalAccountButton() {
        return personalAccountButton;
    }

    public SelenideElement getLogInToAccountButton() {
        return logInToAccountButton;
    }

    public SelenideElement getAssembleBurgerHeader() {
        return assembleBurgerHeader;
    }
}