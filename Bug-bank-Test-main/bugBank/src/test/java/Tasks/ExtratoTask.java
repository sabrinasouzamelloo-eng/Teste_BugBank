package Tasks;

import Framework.Utils.FileOperations;
import Framework.Utils.Moeda;
import PageObjects.ExtratoPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import Validacao.ExtratoValidacao;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public class ExtratoTask {

    private WebDriver driver;
    private HomePage homePage;
    private ExtratoPage extratoPage;
    private LoginPage loginPage;
    private ExtratoValidacao extratoValidacao;

    public ExtratoTask (WebDriver driver){

        this.driver = driver;
        homePage = new HomePage(this.driver);
        extratoPage = new ExtratoPage(this.driver);
        loginPage = new LoginPage(this.driver);
        extratoValidacao = new ExtratoValidacao(this.driver);
    }

    public void validarExtratoOrigem(){

        abrirExtrato();
        BigDecimal valor = valorTransferido();
        extratoValidacao.validaSaldoDisponivel(saldoAbertura().subtract(valor));
        extratoValidacao.validaLinhaTransferencia(descricao(), valor);
    }

    public void validarExtratoDestino(){

        abrirExtrato();
        BigDecimal valor = valorTransferido();
        extratoValidacao.validaSaldoDisponivel(saldoAbertura().add(valor));
        extratoValidacao.validaLinhaTransferencia(descricao(), valor);
    }

    public void sair(){

        extratoPage.getSairButton().click();
        loginPage.getEmailInput();
    }

    private void abrirExtrato(){

        homePage.getExtratoButton().click();
        extratoPage.getSaldoDisponivel();
    }

    private BigDecimal saldoAbertura(){

        return Moeda.paraNumero(extratoPage.getValorAbertura().getText());
    }

    private BigDecimal valorTransferido(){

        return new BigDecimal(FileOperations.getProperties("transferencia").getProperty("valor"));
    }

    private String descricao(){

        return FileOperations.getProperties("transferencia").getProperty("descricao");
    }
}
