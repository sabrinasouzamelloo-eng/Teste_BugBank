package Tasks;

import Framework.Utils.Acoes;
import Framework.Utils.FakersGeneration;
import Framework.Utils.FileOperations;
import Framework.Utils.Textos;
import PageObjects.LoginPage;
import PageObjects.ModalPage;
import PageObjects.RegistroPage;
import Validacao.ModalValidacao;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistroTask {

    private WebDriver driver;
    private LoginPage loginPage;
    private RegistroPage registroPage;
    private ModalPage modalPage;
    private ModalValidacao modalValidacao;
    private FakersGeneration fakersGeneration;

    private String classeToggleDesligado;

    public RegistroTask (WebDriver driver){

        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        registroPage = new RegistroPage(this.driver);
        modalPage = new ModalPage(this.driver);
        modalValidacao = new ModalValidacao(this.driver);
        fakersGeneration = new FakersGeneration();
    }

    public void cadastrarConta(String perfil){

        String nome = fakersGeneration.getFirstName();
        String email = fakersGeneration.getEmailUnico(perfil);
        String senha = FileOperations.getProperties("user").getProperty(perfil + "Senha");

        FileOperations.setProperties("conta", perfil + "Nome", nome);
        FileOperations.setProperties("conta", perfil + "Email", email);

        if (!registroPage.isRegistroVisivel()) {
            Acoes.clicar(driver, loginPage.getRegistrarButton());
        }

        Acoes.preencher(registroPage.getEmailInput(), email);
        Acoes.preencher(registroPage.getNomeInput(), nome);
        Acoes.preencher(registroPage.getSenhaInput(), senha);
        Acoes.preencher(registroPage.getConfirmaSenhaInput(), senha);
        garantirContaComSaldo();
        Acoes.clicar(driver, registroPage.getCadastrarButton());

        modalValidacao.validaMensagemContem("foi criada com sucesso");

        String[] conta = Textos.extrairConta(modalPage.getModalText().getText());
        FileOperations.setProperties("conta", perfil + "Conta", conta[0]);
        FileOperations.setProperties("conta", perfil + "Digito", conta[1]);

        Acoes.clicar(driver, modalPage.getFecharButton());
        modalPage.aguardarFechamento();
    }

    private void garantirContaComSaldo(){

        String classeAtual = registroPage.getToggleSaldoSpan().getAttribute("class");

        if (classeToggleDesligado == null) {
            classeToggleDesligado = classeAtual;
        }

        if (!classeAtual.equals(classeToggleDesligado)) {
            return;
        }

        WebElement toggle = registroPage.getToggleSaldo();

        try {
            toggle.click();
        } catch (WebDriverException e) {
            Acoes.clicarViaJs(driver, toggle);
        }

        if (!aguardarMudancaDoToggle()) {
            Acoes.clicarViaJs(driver, toggle);
        }

        Assertions.assertTrue(aguardarMudancaDoToggle(),
                "Nao consegui ligar o toggle 'Criar conta com saldo'");
    }

    private boolean aguardarMudancaDoToggle(){

        try {

            return new WebDriverWait(driver, Duration.ofSeconds(3)).until(
                    d -> !classeToggleDesligado.equals(registroPage.getToggleSaldoSpan().getAttribute("class")));

        } catch (WebDriverException e) {

            return false;
        }
    }
}
