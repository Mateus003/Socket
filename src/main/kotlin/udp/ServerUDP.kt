package udp

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import kotlin.math.pow

fun main() {
    val serverIp = "127.0.0.1"
    val serverPort = 54321

    val dnsTcpAddress = "127.0.0.1"
    val dnsTcpPort = 1025

    val dnsTcpSocket = DatagramSocket()
    val servico = "register servidorUDP 127.0.0.1 54321"
    val servicoData = servico.toByteArray()
    dnsTcpSocket.send(DatagramPacket(servicoData, servicoData.size, InetAddress.getByName(dnsTcpAddress), dnsTcpPort))

    val ipBuffer = ByteArray(1024)
    val ipPacket = DatagramPacket(ipBuffer, ipBuffer.size)
    dnsTcpSocket.receive(ipPacket)

    println("Conexão DNS estabelecida ${ipPacket.address}:${ipPacket.port}")
    println(String(ipPacket.data, 0, ipPacket.length))

    dnsTcpSocket.close()

    val serverSocket = DatagramSocket(serverPort)
    println("Servidor UDP aguardando conexões em $serverIp:$serverPort")
    println("")
    println("Servidor esperando solicitações...")
    println("")

    fun calculadora(operacao: String): String {
        val listaOperacao = operacao.split(" ")

        if (listaOperacao.isEmpty()) {
            return "Operação inválida"
        }

        val num1 = listaOperacao[0].toInt()
        val sinal = listaOperacao[1]
        val num2 = listaOperacao[2].toInt()

        val resultado = when (sinal) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> {
                if (num2 == 0) {
                    return "Divisão por zero não é permitida."
                }
                num1.toDouble() / num2.toDouble()
            }
            "**" -> num1.toDouble().pow(num2).toInt()
            else -> return "Operação não suportada."
        }

        return resultado.toString()
    }

    var clientAddress: InetAddress? = null
    var clientPort: Int = 0


    while (true) {
        val buffer = ByteArray(1024)
        val packet = DatagramPacket(buffer, buffer.size)
        serverSocket.receive(packet)

        if (clientAddress == null || clientPort == 0) {
            clientAddress = packet.address
            clientPort = packet.port
            println("Conexão realizada com sucesso\uD83D\uDFE2")
            println("Conexão de $clientAddress:$clientPort")
            println("")
        }

        val data = String(packet.data, 0, packet.length)
        val operacao = data

        if (operacao == "fim") {
            break
        }

        println("Equação recebida: $operacao")

        val resposta = calculadora(operacao)
        val respostaData = resposta.toByteArray()
        val responsePacket = DatagramPacket(respostaData, respostaData.size, clientAddress, clientPort)

        serverSocket.send(responsePacket)
        println("Resposta enviada: $resposta")
    }

    serverSocket.close()
}
