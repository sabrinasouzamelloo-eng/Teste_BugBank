package TestCases;

import Framework.TestBase;
import Framework.Utils.TakeScreenshot;
import Tasks.ExtratoTask;
import Tasks.HomeTask;
import Tasks.LoginTask;
import Tasks.RegistroTask;
import Tasks.TransferenciaTask;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

@Epic("Teste Banco")
@Feature("Transferencia entre contas")
public class TransferenciaEntreContasTest extends TestBase {

    private final WebDriver driver = getDriverManager();

    RegistroTask registroTask = new RegistroTask(driver);
    LoginTask loginTask = new LoginTask(driver);
    HomeTask homeTask = new HomeTask(driver);
    TransferenciaTask transferenciaTask = new TransferenciaTask(driver);
    ExtratoTask extratoTask = new ExtratoTask(driver);

    @Test
    @Story("Usuario transfere valor entre duas contas criadas com saldo")
    @Description("Cadastra duas contas, transfere da primeira para a segunda e valida o extrato das duas")
    public void transferirEntreContas() {

        try {

            registroTask.cadastrarConta("origem");
            registroTask.cadastrarConta("destino");

            loginTask.efetuarLogin("origem");
            homeTask.validarHome("origem");
            transferenciaTask.transferir();
            extratoTask.validarExtratoOrigem();
            extratoTask.sair();

            loginTask.efetuarLogin("destino");
            homeTask.validarHome("destino");
            extratoTask.validarExtratoDestino();

        } catch (AssertionError | RuntimeException e) {

            TakeScreenshot.screenshotPage(driver, "ERRO");
            throw e;
        }
    }
}
