package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegistroPage {

    private static final By EMAIL = By.xpath("//div[@class='card__register']//input[@name='email']");
    private static final By NOME = By.xpath("//div[@class='card__register']//input[@name='name']");
    private static final By SENHA = By.xpath("//div[@class='card__register']//input[@name='password']");
    private static final By CONFIRMA_SENHA = By.xpath("//input[@name='passwordConfirmation']");
    private static final By TOGGLE_SALDO = By.xpath("//label[@id='toggleAddBalance']");
    private static final By TOGGLE_SALDO_SPAN = By.xpath("//label[@id='toggleAddBalance']/following-sibling::span");
    private static final By CADASTRAR = By.xpath("//button[text()='Cadastrar']");
    private static final By VOLTAR_LOGIN = By.xpath("//a[@id='btnBackButton']");

    private WebDriver driver;
    private Waits waits;

    public RegistroPage(WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    public WebElement getEmailInput(){

        return waits.visbilityOfElement(EMAIL);
    }

    public WebElement getNomeInput(){

        return waits.visbilityOfElement(NOME);
    }

    public WebElement getSenhaInput(){

        return waits.visbilityOfElement(SENHA);
    }

    public WebElement getConfirmaSenhaInput(){

        return waits.visbilityOfElement(CONFIRMA_SENHA);
    }

    public WebElement getToggleSaldo(){

        return waits.presenceOfElement(TOGGLE_SALDO);
    }


    public WebElement getToggleSaldoSpan(){

        return waits.presenceOfElement(TOGGLE_SALDO_SPAN);
    }

    public WebElement getCadastrarButton(){

        return waits.clickableElement(CADASTRAR);
    }

    public WebElement getVoltarLoginLink(){

        return waits.clickableElement(VOLTAR_LOGIN);
    }

    public boolean isRegistroVisivel(){

        List<WebElement> campos = driver.findElements(EMAIL);
        return !campos.isEmpty() && campos.get(0).isDisplayed();
    }
}
