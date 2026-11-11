package Validacao;

import Framework.Utils.Moeda;
import Framework.Utils.TakeScreenshot;
import PageObjects.HomePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public class HomeValidacao {

    private WebDriver driver;
    private HomePage homePage;

    public HomeValidacao (WebDriver driver){

        this.driver = driver;
        homePage = new HomePage(this.driver);
    }

    public void validaUsuarioLogado(String nome){

        try {

            String texto = homePage.getTextName().getText();
            Assertions.assertTrue(texto.contains(nome),
                    "Home deveria cumprimentar '" + nome + "', mas mostrou: '" + texto + "'");
            TakeScreenshot.screenshotPage(driver, "Home");

        } catch (AssertionError | RuntimeException e) {

            TakeScreenshot.screenshotPage(driver, "Home");
            throw e;
        }
    }


    public void validaNumeroConta(String numero, String digito){

        try {

            Assertions.assertEquals(numero + "-" + digito, homePage.getNumeroConta().getText().trim());

        } catch (AssertionError | RuntimeException e) {

            TakeScreenshot.screenshotPage(driver, "Numero da conta");
            throw e;
        }
    }


    public void validaSaldoPositivo(){

        try {

            BigDecimal saldo = Moeda.paraNumero(homePage.getSaldo().getText());
            Assertions.assertTrue(saldo.compareTo(BigDecimal.ZERO) > 0,
                    "A conta deveria ter saldo, mas o saldo e " + saldo);

        } catch (AssertionError | RuntimeException e) {

            TakeScreenshot.screenshotPage(driver, "Saldo da conta");
            throw e;
        }
    }
}
