package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TransferenciaPage {

    private static final By TEXTO_TELA = By.xpath("//p[contains(text(),'Realize transferência')]");
    private static final By NUMERO_CONTA = By.xpath("//input[@name='accountNumber']");
    private static final By DIGITO = By.xpath("//input[@name='digit']");
    private static final By VALOR = By.xpath("//input[@name='transferValue']");
    private static final By DESCRICAO = By.xpath("//input[@name='description']");
    private static final By TRANSFERIR = By.xpath("//button[text()='Transferir agora']");
    private static final By VOLTAR = By.xpath("//a[@id='btnBack']");

    private WebDriver driver;
    private Waits waits;

    public TransferenciaPage(WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    public WebElement getTextoTela(){

        return waits.visbilityOfElement(TEXTO_TELA);
    }

    public WebElement getNumeroContaInput(){

        return waits.visbilityOfElement(NUMERO_CONTA);
    }

    public WebElement getDigitoInput(){

        return waits.visbilityOfElement(DIGITO);
    }

    public WebElement getValorInput(){

        return waits.visbilityOfElement(VALOR);
    }

    public WebElement getDescricaoInput(){

        return waits.visbilityOfElement(DESCRICAO);
    }

    public WebElement getTransferirButton(){

        return waits.clickableElement(TRANSFERIR);
    }

    public WebElement getVoltarButton(){

        return waits.clickableElement(VOLTAR);
    }
}
