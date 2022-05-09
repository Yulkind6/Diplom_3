import com.codeborne.selenide.Condition;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class ConstructorTest {

    ConstructorPage constructorPage;

    @Test
    @DisplayName("Конструктор. Переход к вкладке Соусы")
    public void tabTransitionsToSaucesInConstructorSuccessTest() {
        constructorPage = open(Base.BASE_URL, ConstructorPage.class);

        constructorPage.getSaucesTab().click();
        constructorPage.getSaucesHeader().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Конструктор. Переход к вкладке Начинки")
    public void tabTransitionsToFillingInConstructorSuccessTest() {
        constructorPage = open(Base.BASE_URL, ConstructorPage.class);

        constructorPage.getFillingTab().click();
        constructorPage.getFillingHeader().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Конструктор. Переход к вкладке Булки")
    public void tabTransitionsToBurgersInConstructorSuccessTest() {
        constructorPage = open(Base.BASE_URL, ConstructorPage.class);
        constructorPage.getSaucesTab().click();
        constructorPage.getSaucesHeader().shouldBe(Condition.visible);

        constructorPage.getBurgersTab().click();
        constructorPage.getBurgersHeader().shouldBe(Condition.visible);
    }
}