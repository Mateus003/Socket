# Projeto de Sockets - Redes de Computadores(IF975)
- **Professor:** Kelvin Lopes Dias
- **Aluno:** Mateus da Silva Olegario
## Sobre o Projeto:
O projeto em Kotlin que desenvolvi é uma aplicação de sockets que permite realizar operações matemáticas através da comunicação via UDP ou TCP. Este projeto foi organizado em duas pastas principais: "tcp" e "udp", onde os arquivos "ClientTCP" e "ServerTCP" foram colocados na pasta "tcp", enquanto os arquivos "ClientUDP" e "ServerUDP" foram colocados na pasta "udp". Além disso, o arquivo relacionado ao sistema de nomes de domínio (DNS) foi mantido separado das pastas UDP e TCP.
#### Para implementar a funcionalidade de sockets, foram utilizadas as seguintes bibliotecas: 

- **java.net.InetAddress:** Essa biblioteca foi utilizada para lidar com endereços IP e nomes de hosts, permitindo a resolução de nomes de domínio em endereços IP.

 - **kotlin.math.pow:** A biblioteca Kotlin Math foi usada para realizar cálculos matemáticos, o que pode ser relevante para as operações matemáticas realizadas no projeto.

- **java.net.DatagramPacket e java.net.DatagramSocket:** Essas bibliotecas foram utilizadas para implementar a comunicação via UDP. O DatagramSocket é usado para criar e gerenciar sockets UDP, enquanto o DatagramPacket é usado para encapsular dados que serão enviados ou recebidos através do UDP.

- **java.net.ServerSocket:** Esta biblioteca foi usada para implementar a comunicação via TCP. O ServerSocket é responsável por criar um servidor TCP que pode aceitar conexões de clientes.

- **java.util.concurrent.ConcurrentHashMap:** Essa biblioteca foi utilizada para gerenciar estruturas de dados compartilhadas de forma segura em ambientes multithread, o que pode ser útil para gerenciar clientes e conexões em paralelo.

- **kotlin.concurrent.thread e java.util.concurrent.Executors:** Essas bibliotecas foram usadas para gerenciar threads e execução de tarefas assíncronas no projeto, tornando-o mais eficiente em termos de concorrência. 

### Fluxo do Projeto:
O projeto foi estruturado seguindo este fluxo específico: começa-se por iniciar o servidor DNS, seguido pela inicialização dos servidores TCP e/ou UDP, que estabelecem conexão com o DNS. Uma vez que estes servidores estejam online, é possível iniciar os clientes TCP e/ou UDP. Quando um cliente efetua uma solicitação, ele pede ao DNS para obter informações sobre o servidor. Em seguida, através dos clientes, é possível enviar operações matemáticas para que sejam calculadas no servidor. Após o processamento, o cliente recebe a resposta da operação e o horário em que ela foi realizada.

### Análise do tempo de requisição:
Observando o terminal do cliente, tanto do TCP, como no UDP, no geral, o tempo total do TCP foi menor em relação ao UDP. Por exemplo,o tempo total da primeira requisição do cliente TCP foi significativamente menor em comparação com o cliente UDP. No cliente TCP, o tempo total foi de apenas 0.01484 milissegundos, enquanto no cliente UDP, a primeira requisição teve um tempo total de 233.115 milisegundos. Essa discrepância inicial de tempo é notável e representa uma exceção em relação ao desempenho esperado desses protocolos. Geralmente, o UDP é mais rápido, mas não oferece as mesmas garantias de confiabilidade que o TCP.
## Como executar o código:
- **Instale o IntelliJ IDEA Community Edition:** Você pode fazer o download do IntelliJ IDEA Community Edition no site de download: https://www.jetbrains.com/idea/

- **Instale a JDK (Java Development Kit):** Certifique-se de que a JDK esteja instalada em sua máquina. Você pode baixar a JDK diretamente do site oficial da Oracle ou optar por uma distribuição OpenJDK de código aberto.

- **Configure o ambiente da IDE:** Siga as instruções fornecidas neste vídeo tutorial para configurar o ambiente do IntelliJ IDEA, garantindo que não haja problemas na execução do código: https://www.youtube.com/watch?v=xRBd2l580Ac&t=19s&ab_channel=StackMobile

- **Baixe o projeto do GitHub:** Clone ou baixe o arquivo zipado do projeto do GitHub e extraia-o para um diretório de sua escolha em sua máquina.

- **Abra o projeto no IntelliJ IDEA:** Na página inicial do IntelliJ, clique em "Open" e selecione a pasta do projeto que você acabou de extrair.

- **Para executar os códigos dos servidores, clientes e do DNS:**  Navegue até as pastas do projeto seguindo este caminho: src/main/kotlin.
Dentro da pasta kotlin, você encontrará o arquivo que contém o código do DNS.
Há também pastas para UDP e TCP, onde o código dos servidores e clientes UDP e TCP está armazenado.
Siga o fluxo do projeto para determinar a ordem de execução adequada.


## Capturas realizadas:
- **Captura do DNS:** ![WhatsApp Image 2023-09-14 at 23 13 32](https://github.com/Mateus003/Socket/assets/102229622/1adbbd1a-a5ef-4260-8245-88bb37f3be72)




- **Captura do Servidor UDP:** ![WhatsApp Image 2023-09-14 at 23 14 10 (1)](https://github.com/Mateus003/Socket/assets/102229622/8556d471-9da9-47f4-ac80-bc072320097a)


- **Captura do Servidor TCP:**![WhatsApp Image 2023-09-14 at 23 13 55](https://github.com/Mateus003/Socket/assets/102229622/8b7c2149-190b-4679-8bed-ecb6c5f28154)


- **Captura do Cliente UDP:**![WhatsApp Image 2023-09-14 at 23 13 32](https://github.com/Mateus003/Socket/assets/102229622/f5f083b8-08f9-44af-901e-989ef1155716)


- **Captura do Cliente TCP:**![WhatsApp Image 2023-09-14 at 23 14 23 (1)](https://github.com/Mateus003/Socket/assets/102229622/112c8804-f803-4b1c-ac9c-11309c5670fc)

-  **Arquivo das capturas do Wireshark:** 
https://drive.google.com/file/d/1FMe8KcO3nntXyWFBxVEqfkqfsmvPonCI/view?usp=drive_link


