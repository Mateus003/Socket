package tcp

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.ServerSocket
import java.util.concurrent.Executors

fun main() {
    val serverIp = "127.0.0.1"
    val serverPort = 12345

    val dnsUdpSocket = DatagramSocket()
    val dnsServerAddress = InetAddress.getByName(serverIp)

    val mensagem = "register servidorTCP 127.0.0.1 12345"
    val dnsPacket = DatagramPacket(
        mensagem.toByteArray(),
        mensagem.length,
        InetAddress.getByName("127.0.0.1"),
        1025
    )
    dnsUdpSocket.send(dnsPacket)

    val dnsResponseBuffer = ByteArray(1024)
    val dnsResponsePacket = DatagramPacket(dnsResponseBuffer, dnsResponseBuffer.size)
    dnsUdpSocket.receive(dnsResponsePacket)

    val dnsResponse = String(dnsResponsePacket.data, 0, dnsResponsePacket.length)
    println("Conexão DNS estabelecida ${dnsResponsePacket.address}:${dnsResponsePacket.port}")
    println(dnsResponse)

    dnsUdpSocket.close()

    val serverSocket = ServerSocket(serverPort)
    println("Servidor TCP aguardando conexões em $serverIp:$serverPort")

    println("")
    println("Servidor esperando solicitações...")
    println("")

    val executorService = Executors.newCachedThreadPool()

    try {
        while (true) {
            val clientSocket = serverSocket.accept()
            println("Conexão realizada com sucesso\uD83D\uDFE2")
            println("Conexão do cliente ${clientSocket.inetAddress}:${clientSocket.port}")
            println("")

            executorService.submit {
                val input = clientSocket.getInputStream()
                val output = clientSocket.getOutputStream()

                while (true) {
                    val buffer = ByteArray(1024)
                    val bytesRead = input.read(buffer)
                    if (bytesRead == -1) break

                    val operacao = String(buffer, 0, bytesRead)
                    if (operacao == "fim") break

                    println("Equação recebida: $operacao")

                    val resposta = calculadora(operacao).toString()
                    output.write(resposta.toByteArray())
                    println("Resposta enviada: $resposta")
                }

                clientSocket.close()
            }
        }
    } finally {
        serverSocket.close()
    }
}

fun calculadora(operacao: String): Any {
    val listaOperacao = operacao.split(" ")

    if (listaOperacao.isEmpty()) {
        return 2
    }

    val num1 = listaOperacao[0].toInt()
    val num2 = listaOperacao[2].toInt()
    val sinal = listaOperacao[1]

    return when (sinal) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "/" -> if (num2 == 0) "Divisão por zero não é permitida." else num1.toDouble() / num2
        "*" -> num1 * num2
        "**" -> Math.pow(num1.toDouble(), num2.toDouble())
        else -> "Operação não suportada."
    }
}
