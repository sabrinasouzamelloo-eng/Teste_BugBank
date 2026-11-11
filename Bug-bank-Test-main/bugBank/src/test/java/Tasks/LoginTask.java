package Tasks;

import Framework.Utils.Acoes;
import Framework.Utils.FileOperations;
import PageObjects.LoginPage;
import PageObjects.RegistroPage;
import Validacao.LoginValidacao;
import org.openqa.selenium.WebDriver;

public class LoginTask {

    private WebDriver driver;
    private LoginPage loginPage;
    private RegistroPage registroPage;
    private LoginValidacao loginValidacao;

    public LoginTask (WebDriver driver){

        this.driver = driver;
        loginPage = new LoginPage(this.driver);
        registroPage = new RegistroPage(this.driver);
        loginValidacao = new LoginValidacao(this.driver);
    }

    public void efetuarLogin(String perfil){

        String email = FileOperations.getProperties("conta").getProperty(perfil + "Email");
        String senha = FileOperations.getProperties("user").getProperty(perfil + "Senha");

        if (!loginPage.isLoginVisivel()) {
            Acoes.clicar(driver, registroPage.getVoltarLoginLink());
        }

        loginValidacao.loginValidation();
        Acoes.preencher(loginPage.getEmailInput(), email);
        Acoes.preencher(loginPage.getPasswordInput(), senha);
        Acoes.clicar(driver, loginPage.getAcessarButton());
    }
}
