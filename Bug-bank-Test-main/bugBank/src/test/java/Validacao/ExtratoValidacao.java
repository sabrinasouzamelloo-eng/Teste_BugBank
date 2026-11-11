package Validacao;

import Framework.Utils.Moeda;
import Framework.Utils.TakeScreenshot;
import PageObjects.ExtratoPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public class ExtratoValidacao {

    private WebDriver driver;
    private ExtratoPage extratoPage;

    public ExtratoValidacao (WebDriver driver){

        this.driver = driver;
        extratoPage = new ExtratoPage(this.driver);
    }

    public void validaSaldoDisponivel(BigDecimal esperado){

        try {

            BigDecimal atual = Moeda.paraNumero(extratoPage.getSaldoDisponivel().getText());
            Assertions.assertEquals(0, esperado.compareTo(atual),
                    "Saldo disponivel esperado " + esperado + ", mas o extrato mostra " + atual);
            TakeScreenshot.screenshotPage(driver, "Extrato: saldo");

        } catch (AssertionError | RuntimeException e) {

            TakeScreenshot.screenshotPage(driver, "Extrato: saldo");
            throw e;
        }
    }


    public void validaLinhaTransferencia(String descricao, BigDecimal valor){

        try {

            BigDecimal atual = Moeda.paraNumero(extratoPage.getValorPorDescricao(descricao).getText()).abs();
            Assertions.assertEquals(0, valor.abs().compareTo(atual),
                    "Valor da transferencia esperado " + valor + ", mas o extrato mostra " + atual);
            TakeScreenshot.screenshotPage(driver, "Extrato: transferencia");

        } catch (AssertionError | RuntimeException e) {

            TakeScreenshot.screenshotPage(driver, "Extrato: transferencia");
            throw e;
        }
    }
}
