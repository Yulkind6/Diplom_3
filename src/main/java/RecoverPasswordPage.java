import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RecoverPasswordPage {

    //локатор ссылки "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement loginLink;

    public SelenideElement getLoginLink() {
        return loginLink;
    }
}