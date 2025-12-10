# EGarcom
Alunos: Nayara Maria Costa De Mesquita - 2025200253
Edney Lincoln de Queiroz Lourenço - 2025200225 

Importação e Execução do Projeto Java (Maven/Spring Boot)
1. No IntelliJ IDEA (Recomendado para Projetos Java)
O IntelliJ tem o suporte mais robusto e nativo para projetos Maven e Spring Boot.

A. Importação do Projeto
Abrir a IDE: Inicie o IntelliJ IDEA.

Opção "Open" (Abrir): Na tela inicial, clique em "Open" (Abrir).

Selecionar a Pasta: Navegue até a pasta raiz do seu projeto (/garcom) e clique em "OK" ou "Open".

Alternativa: Você também pode selecionar diretamente o ficheiro pom.xml. O IntelliJ fará a importação do projeto automaticamente, reconhecendo-o como um projeto Maven.

Aguardar a Sincronização: O IntelliJ irá detectar o pom.xml, baixar todas as dependências (Spring Boot, JPA, H2, etc.) e indexar os ficheiros. Você verá uma barra de progresso no canto inferior.

B. Execução do Projeto
Localizar a Classe Principal: No painel do projeto (Project Tool Window), navegue até a classe principal: src/main/java/com/example/garcom/GarcomApplication.java.

Executar:

Clique com o botão direito na classe GarcomApplication.java.

Selecione "Run 'GarcomApplication.main()'".

Ou: Use o botão verde Play/Run na barra de ferramentas ou ao lado da declaração do método main.

Verificar: O servidor será iniciado (porta 8080 por padrão) e você verá a seguinte mensagem no console, confirmando que o H2 e o Swagger estão prontos:

Swagger UI: Acessível em http://localhost:8080/swagger-ui.html

Console H2: Acessível em http://localhost:8080/h2

2. No VS Code (Com Extensões)
O VS Code requer algumas extensões, mas é igualmente eficaz e leve.

A. Configuração Inicial (Extensões)
Certifique-se de que você tem as seguintes extensões instaladas no VS Code (geralmente instaladas através do Java Extension Pack):

Extension Pack for Java

Maven for Java

Spring Boot Tools

B. Importação do Projeto
Abrir a IDE: Inicie o VS Code.

Opção "Open Folder" (Abrir Pasta): Vá em File (Arquivo) > Open Folder... (Abrir Pasta...).

Selecionar a Pasta: Selecione a pasta raiz do projeto (/garcom).

Aguardar a Configuração: O VS Code detectará o pom.xml e as extensões Java e Maven começarão a baixar as dependências e a configurar o ambiente.

C. Execução do Projeto
Abrir a Classe Principal: Abra o ficheiro src/main/java/com/example/garcom/GarcomApplication.java.

Executar:

Acima da declaração da classe, você verá um Code Lens escrito "Run" (Executar). Clique nele.

Alternativa: Abra a aba "Run and Debug" (Executar e Depurar) no menu lateral e selecione a configuração de "Spring Boot App" para iniciar a aplicação.

Verificar: A saída do console aparecerá no painel "DEBUG CONSOLE", confirmando que o servidor está ativo.
