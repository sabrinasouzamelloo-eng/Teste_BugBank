package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage {

    private static final By EMAIL = By.xpath("//div[@class='card__login']//input[@name='email']");
    private static final By SENHA = By.xpath("//div[@class='card__login']//input[@name='password']");
    private static final By ACESSAR = By.xpath("//div[@class='card__login']//button[text()='Acessar']");
    private static final By REGISTRAR = By.xpath("//div[@class='card__login']//div[@class='login__buttons']/button[@type='button']");

    private WebDriver driver;
    private Waits waits;

    public LoginPage(WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    public WebElement getEmailInput(){

        return waits.visbilityOfElement(EMAIL);
    }

    public WebElement getPasswordInput(){

        return waits.visbilityOfElement(SENHA);
    }

    public WebElement getAcessarButton(){

        return waits.clickableElement(ACESSAR);
    }

    public WebElement getRegistrarButton(){

        return waits.clickableElement(REGISTRAR);
    }

    public boolean isLoginVisivel(){

        List<WebElement> campos = driver.findElements(EMAIL);
        return !campos.isEmpty() && campos.get(0).isDisplayed();
    }
}
