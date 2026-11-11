package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private static final By NOME_USUARIO = By.xpath("//p[@id='textName']");
    private static final By NUMERO_CONTA = By.xpath("//p[@id='textAccountNumber']/span");
    private static final By SALDO = By.xpath("//p[@id='textBalance']/span");
    private static final By TRANSFERENCIA = By.xpath("//a[starts-with(@id,'btn-TRANSFER')]");
    private static final By EXTRATO = By.xpath("//a[@id='btn-EXTRATO']");

    private WebDriver driver;
    private Waits waits;

    public HomePage(WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    public WebElement getTextName(){

        return waits.visbilityOfElement(NOME_USUARIO);
    }

    public WebElement getNumeroConta(){

        return waits.visbilityOfElement(NUMERO_CONTA);
    }

    public WebElement getSaldo(){

        return waits.visbilityOfElement(SALDO);
    }

    public WebElement getTransferenciaButton(){

        return waits.clickableElement(TRANSFERENCIA);
    }

    public WebElement getExtratoButton(){

        return waits.clickableElement(EXTRATO);
    }
}
