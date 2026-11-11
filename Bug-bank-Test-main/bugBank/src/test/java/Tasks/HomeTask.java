package Tasks;

import Framework.Utils.FileOperations;
import Validacao.HomeValidacao;
import org.openqa.selenium.WebDriver;

public class HomeTask {

    private WebDriver driver;
    private HomeValidacao homeValidacao;

    public HomeTask (WebDriver driver){

        this.driver = driver;
        homeValidacao = new HomeValidacao(this.driver);
    }

    public void validarHome(String perfil){

        String nome = FileOperations.getProperties("conta").getProperty(perfil + "Nome");
        String numero = FileOperations.getProperties("conta").getProperty(perfil + "Conta");
        String digito = FileOperations.getProperties("conta").getProperty(perfil + "Digito");

        homeValidacao.validaUsuarioLogado(nome);
        homeValidacao.validaNumeroConta(numero, digito);
        homeValidacao.validaSaldoPositivo();
    }
}
