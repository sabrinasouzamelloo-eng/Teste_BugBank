package Validacao;

import Framework.Utils.TakeScreenshot;
import PageObjects.TransferenciaPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class TransferenciaValidacao {

    private WebDriver driver;
    private TransferenciaPage transferenciaPage;

    public TransferenciaValidacao (WebDriver driver){

        this.driver = driver;
        transferenciaPage = new TransferenciaPage(this.driver);
    }

    public void validaTelaTransferencia(){

        try {

            Assertions.assertTrue(transferenciaPage.getTextoTela().isDisplayed());
            TakeScreenshot.screenshotPage(driver, "Tela de transferencia");

        } catch (AssertionError | RuntimeException e) {

            TakeScreenshot.screenshotPage(driver, "Tela de transferencia");
            throw e;
        }
    }
}
