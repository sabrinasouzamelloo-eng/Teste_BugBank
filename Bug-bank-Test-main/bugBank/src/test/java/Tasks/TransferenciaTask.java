package Tasks;

import Framework.Utils.Acoes;
import Framework.Utils.FileOperations;
import PageObjects.HomePage;
import PageObjects.ModalPage;
import PageObjects.TransferenciaPage;
import Validacao.ModalValidacao;
import Validacao.TransferenciaValidacao;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class TransferenciaTask {

    private WebDriver driver;
    private HomePage homePage;
    private TransferenciaPage transferenciaPage;
    private ModalPage modalPage;
    private TransferenciaValidacao transferenciaValidacao;
    private ModalValidacao modalValidacao;

    public TransferenciaTask (WebDriver driver){

        this.driver = driver;
        homePage = new HomePage(this.driver);
        transferenciaPage = new TransferenciaPage(this.driver);
        modalPage = new ModalPage(this.driver);
        transferenciaValidacao = new TransferenciaValidacao(this.driver);
        modalValidacao = new ModalValidacao(this.driver);
    }

    public void transferir(){

        Properties conta = FileOperations.getProperties("conta");
        Properties transferencia = FileOperations.getProperties("transferencia");

        homePage.getTransferenciaButton().click();
        transferenciaValidacao.validaTelaTransferencia();

        Acoes.preencher(transferenciaPage.getNumeroContaInput(), conta.getProperty("destinoConta"));
        Acoes.preencher(transferenciaPage.getDigitoInput(), conta.getProperty("destinoDigito"));
        Acoes.preencher(transferenciaPage.getValorInput(), transferencia.getProperty("valor"));
        Acoes.preencher(transferenciaPage.getDescricaoInput(), transferencia.getProperty("descricao"));
        Acoes.clicar(driver, transferenciaPage.getTransferirButton());

        modalValidacao.validaMensagemContem("realizada com sucesso");
        Acoes.clicar(driver, modalPage.getFecharButton());
        modalPage.aguardarFechamento();

        transferenciaPage.getVoltarButton().click();
        homePage.getTextName();
    }
}
