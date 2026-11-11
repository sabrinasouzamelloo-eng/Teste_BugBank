package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExtratoPage {

    private static final String DESCRICAO_ABERTURA = "Saldo adicionado ao abrir conta";

    private static final By SALDO_DISPONIVEL = By.xpath("//p[@id='textBalanceAvailable']");
    private static final By SAIR = By.xpath("//a[@id='btnExit']");

    private WebDriver driver;
    private Waits waits;

    public ExtratoPage(WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    public WebElement getSaldoDisponivel(){

        return waits.visbilityOfElement(SALDO_DISPONIVEL);
    }

    public WebElement getValorPorDescricao(String descricao){

        return waits.visbilityOfElement(By.xpath(
                "//p[@id='textDescription' and text()='" + descricao + "']"
                        + "/following-sibling::p[@id='textTransferValue']"));
    }

    public WebElement getValorAbertura(){

        return getValorPorDescricao(DESCRICAO_ABERTURA);
    }

    public WebElement getSairButton(){

        return waits.clickableElement(SAIR);
    }
}
