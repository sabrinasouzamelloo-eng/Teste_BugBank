# Teste_BugBank
# Automação de Testes - BugBank

Projeto de automação de testes end-to-end (E2E) para a aplicação web [BugBank](https://bugbank.netlify.app/) utilizando Selenium WebDriver, Java e arquitetura Page Objects.

## 🛠️ Tecnologias e Ferramentas
* **Java 26** (com recursos de Preview habilitados)
* **Selenium WebDriver** (Automação web)
* **JUnit 5** (Gerenciamento e execução de testes)
* **WebDriverManager** (Gerenciamento automático de drivers)
* **Maven** (Gerenciamento de dependências e build)
* **Allure Reports** (Relatórios de execução)

---

## 📂 Estrutura do Projeto
* `src/main/java/Framework/` -> Configurações de driver, base de testes e utilitários (ações, manipulação de arquivos, conversão de moeda).
* `src/test/java/PageObjects/` -> Mapeamento de elementos e telas da aplicação.
* `src/test/java/Tasks/` -> Ações e fluxos de negócio executados nos testes.
* `src/test/java/Validacao/` -> Assertions e validações de tela.
* `src/test/java/TestCases/` -> Casos de teste executáveis.
* `src/main/resources/Properties/` -> Arquivos de massa de dados e propriedades (`conta.properties`, `transferencia.properties`, etc.).

---

## 🚀 Como Executar os Testes

1. Certifique-se de estar com a **JDK 26** configurada na sua máquina.
2. Abra a pasta do projeto no seu terminal ou IDE (IntelliJ IDEA).
3. Execute o comando Maven para baixar as dependências e rodar os testes:

```bash
mvn clean test
