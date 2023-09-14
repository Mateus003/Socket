package udp

import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.util.concurrent.TimeUnit

fun main() {
    val serverIp = "127.0.0.1"
    val serverPort = 64321

    val dnsTcpAddress = "127.0.0.1"
    val dnsTcpPort = 1025

    val clientDnsSocket = DatagramSocket()
    val servico = "servidorUDP"
    val servicoData = servico.toByteArray()
    clientDnsSocket.send(DatagramPacket(servicoData, servicoData.size, InetAddress.getByName(dnsTcpAddress), dnsTcpPort))

    val ipBuffer = ByteArray(1024)
    val ipPacket = DatagramPacket(ipBuffer, ipBuffer.size)
    clientDnsSocket.receive(ipPacket)

    println("Solicitação DNS realizada com sucesso")

    val host = String(ipPacket.data, 0, ipPacket.length)
    val lista = host.split(":")
    val enderecoIP = lista[1]
    val porta = lista[2].toInt()

    println("Endereço e porta do servidor: $enderecoIP:$porta")

    val clientSocket = DatagramSocket()
    println("Conexão realizada com sucesso\uD83D\uDFE2")
    println("")
    println("Realize a operação conforme o exemplo: 2 + 2")

    val equacoes = listOf("89 + 1", "10 * 2", "-100 - 23",  "5 ** 0", "40 / 0")

    for (equacao in equacoes) {

        val startTime = System.nanoTime()
        val equacaoData = equacao.toByteArray()
        val equacaoPacket = DatagramPacket(equacaoData, equacaoData.size, InetAddress.getByName(enderecoIP), porta)

        clientSocket.send(equacaoPacket)

        val responseBuffer = ByteArray(1024)
        val responsePacket = DatagramPacket(responseBuffer, responseBuffer.size)
        clientSocket.receive(responsePacket)

        val response = String(responsePacket.data, 0, responsePacket.length)

        val endTime = System.nanoTime()

        println("")
        println("O resultado da operação $equacao é $response")
        println("Tempo total: ${(endTime - startTime) / 1e5} segundos")
    }

    val servicoRemover = "deletar servidorUDP"
    val servicoRemoverData = servicoRemover.toByteArray()
    clientDnsSocket.send(DatagramPacket(servicoRemoverData, servicoRemoverData.size, InetAddress.getByName(dnsTcpAddress), dnsTcpPort))

    print("Digite \"fim\" para finalizar a conexão: ")
    val mensagem = readLine() ?: ""
    val mensagemData = mensagem.toByteArray()
    val mensagemPacket = DatagramPacket(mensagemData, mensagemData.size, InetAddress.getByName(enderecoIP), porta)

    clientSocket.send(mensagemPacket)

    clientDnsSocket.close()
    clientSocket.close()
}
