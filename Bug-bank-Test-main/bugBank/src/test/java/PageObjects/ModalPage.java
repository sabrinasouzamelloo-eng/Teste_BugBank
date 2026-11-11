package PageObjects;

import Framework.Browser.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ModalPage {

    private static final By TEXTO = By.xpath("//p[@id='modalText']");
    private static final By FECHAR = By.xpath("//a[@id='btnCloseModal']");

    private WebDriver driver;
    private Waits waits;

    public ModalPage(WebDriver driver){

        this.driver = driver;
        waits = new Waits(this.driver);
    }

    public WebElement getModalText(){

        return waits.visbilityOfElement(TEXTO);
    }

    public WebElement getFecharButton(){

        return waits.clickableElement(FECHAR);
    }

    public void aguardarFechamento(){

        waits.invisibilityOfElement(TEXTO);
    }
}
