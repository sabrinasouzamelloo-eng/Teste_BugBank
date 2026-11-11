# BugBank - Automação Web

Teste automatizado de transferência entre contas no [BugBank](https://bugbank.netlify.app/).

## Fluxo do teste

1. Acessa a página do BugBank
2. Cadastra duas contas com saldo (origem e destino)
3. Faz login na conta de origem e transfere um valor para a conta de destino
4. Acessa o extrato da conta de origem e valida o saldo (saldo inicial - valor transferido)
5. Faz login na conta de destino e valida no extrato o valor recebido (saldo inicial + valor recebido)

## Tecnologias

Java 27 - Maven - Selenium 4 - JUnit - Datafaker - Allure

## Estrutura

```
src/main/java/Framework     driver, esperas, TestBase e utilitários
src/main/resources          properties (url, senhas, dados da transferência, contas geradas)
src/test/java/PageObjects   mapeamento dos elementos (XPath) de cada tela
src/test/java/Tasks         ações do usuário em cada tela
src/test/java/Validacao     asserts e prints (Allure)
src/test/java/TestCases     cenário de teste
docs/                       mapeamento dos elementos do site
```

## Pré-requisitos

- **JDK 27** (o `pom.xml` compila com `release 27`)
- **Maven 3.9+**
- **Google Chrome** instalado (o WebDriverManager baixa o driver sozinho)
- Internet (o teste acessa o site do BugBank)

## Configurando o Java 27 (JAVA_HOME)

O Maven usa o Java apontado pela variável `JAVA_HOME`. Se ela não existir, ele usa o
`java` do Path, que pode ser outra versão, e o build falha com:

```
Fatal error compiling: error: invalid target release: 27
```

Para configurar no Windows, rode uma vez (trocando pelo caminho do seu JDK 27):

```
setx JAVA_HOME "C:\Users\SEU_USUARIO\.jdks\openjdk-27.0.1.0.3"
```

Ou pela interface: **Editar as variáveis de ambiente do sistema → Variáveis de Ambiente →
Variáveis de usuário → Novo**, com nome `JAVA_HOME` e o caminho do JDK 27 como valor.

Depois **feche e abra o terminal de novo**. O `setx` não vale no terminal que já estava aberto.

### Como saber se funcionou

```
mvn -v
```

A saída precisa mostrar a versão 27, por exemplo:

```
Apache Maven 3.10.0
Java version: 27.0.1, vendor: ..., runtime: C:\Users\...\.jdks\openjdk-27.0.1.0.3
```

Se o `mvn -v` mostrar uma versão diferente da que você configurou, o terminal ainda não
enxerga o `JAVA_HOME`: abra um terminal novo ou confira o valor com `echo %JAVA_HOME%`
(cmd) ou `$env:JAVA_HOME` (PowerShell).

> O `java -version` pode continuar mostrando outra versão, porque o Path do sistema tem
> prioridade. Isso não atrapalha: o Maven segue o `JAVA_HOME`.

## Usando outra versão do Java

O projeto foi desenvolvido e testado com o **JDK 27**, mas é permitido adaptá-lo para
a versão do Java instalada na sua máquina. Nesse caso, será necessário alterar alguns
pontos do projeto:

1. **`pom.xml`**: trocar a versão em `release` (ou em `maven.compiler.release`,
   `source` e `target`, conforme estiver no arquivo) para a versão que você vai usar.
   Exemplo, para Java 21:

   ```xml
   <maven.compiler.release>21</maven.compiler.release>
   ```

2. **`JAVA_HOME`**: apontar para o mesmo JDK escolhido no passo anterior (veja a seção
   acima) e abrir um terminal novo.

3. **Código-fonte**: se alguma classe usar recursos de linguagem ou APIs que só existem
   em versões mais novas do Java, será preciso reescrevê-los de forma compatível com a
   versão escolhida. Rode `mvn clean test` e confira se há erros de compilação.

4. **IntelliJ**: ajustar o SDK e o *Language level* em File → Project Structure.

> A versão escolhida precisa ser compatível com as dependências do projeto (Selenium 4,
> JUnit, Allure). O Selenium 4 exige, no mínimo, Java 11.

## Como executar

Dentro da pasta do projeto (onde fica o `pom.xml`):

```
mvn clean test
```

Não mexa na janela enquanto o teste roda.

### Como saber se o teste passou

No final da saída do terminal deve aparecer:

```
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

O resultado também fica em `target/surefire-reports/`.

### Prints (evidências)

Cada validação tira um print da tela. Depois do `mvn clean test` eles ficam em
`target/prints/`, numerados na ordem do fluxo:

```
01_Modal_foi_criada_com_sucesso.png    cadastro da conta de origem
02_Modal_foi_criada_com_sucesso.png    cadastro da conta de destino
03_Login.png                           login da origem
04_Home.png                            home da origem (nome, conta e saldo)
05_Tela_de_transferencia.png
06_Modal_realizada_com_sucesso.png     transferência concluída
07_Extrato_saldo.png                   extrato da origem (saldo debitado)
08_Extrato_transferencia.png           extrato da origem (linha "Transferência enviada")
09_Login.png                           login do destino
10_Home.png                            home do destino
11_Extrato_saldo.png                   extrato do destino (saldo creditado)
12_Extrato_transferencia.png           extrato do destino (linha "Transferência recebida")
```

Se o teste falhar, o print do momento do erro também é salvo, com o nome `ERRO`.
A pasta é apagada a cada `mvn clean`.

### Relatório Allure

Depois do `mvn clean test`, rode:

```
mvn allure:serve
```

O relatório abre no navegador com os prints de cada etapa (cadastro, login, transferência e
extratos). Se o teste falhar, o print do momento do erro aparece como `ERRO`.

### Pelo IntelliJ

Com o SDK do projeto em **openjdk-27** (File → Project Structure → SDK), rode pela aba
**Maven → Lifecycle → test** ou pelo botão ▶ da classe `TransferenciaEntreContasTest`.

## Dados do teste

- Valor e descrição da transferência: `src/main/resources/Properties/transferencia.properties`
- Senhas das contas: `src/main/resources/Properties/user.properties`
- Dados das contas criadas (nome, e-mail, número e dígito) são gravados a cada execução em
  `src/main/resources/Properties/conta.properties`

## Problemas comuns

| Erro | Causa | Solução |
|---|---|---|
| `invalid target release: 27` | Maven rodando com Java antigo | Configurar o `JAVA_HOME` com o JDK 27 e abrir um terminal novo |
| `'mvn' não é reconhecido...` | Maven fora do Path | Adicionar a pasta `bin` do Maven no Path |
| `ElementClickInterceptedException` | Elemento do BugBank por cima do botão | Já tratado em `Acoes.clicar()`, que faz o clique via JavaScript |
| `SessionNotCreatedException` | Chrome desatualizado ou ausente | Atualizar ou instalar o Google Chrome |
