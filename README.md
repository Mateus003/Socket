# Projeto de Sockets - Redes de Computadores(IF968)
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

- **kotlin.concurrent.thread e java.util.concurrent.Executors:** Essas bibliotecas foram usadas para gerenciar threads e execução de tarefas assíncronas no projeto, tornando-o mais eficiente em termos de concorrência. Para implementar a funcionalidade de sockets, foram utilizadas as seguintes bibliotecas:

### Fluxo do Projeto:
O projeto foi estruturado seguindo este fluxo específico: começa-se por iniciar o servidor DNS, seguido pela inicialização dos servidores TCP e/ou UDP, que estabelecem conexão com o DNS. Uma vez que estes servidores estejam online, é possível iniciar os clientes TCP e/ou UDP. Quando um cliente efetua uma solicitação, ele pede ao DNS para obter informações sobre o servidor. Em seguida, através dos clientes, é possível enviar operações matemáticas para que sejam calculadas no servidor. Após o processamento, o cliente recebe a resposta da operação e o horário em que ela foi realizada.
