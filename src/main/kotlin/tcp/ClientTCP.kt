package tcp

import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.Socket
import java.util.concurrent.TimeUnit

fun clientTCP(equacoes: List<String>) {
    val serverIp = "127.0.0.1"
    val serverPort = 12346

    val dnsTcpClient = DatagramSocket()

    val servico = "servidorTCP".toByteArray()
    val dnsPacket = DatagramPacket(servico, servico.size, InetAddress.getByName("127.0.0.1"), 1025)
    dnsTcpClient.send(dnsPacket)

    val ipBuffer = ByteArray(1024)
    val ipPacket = DatagramPacket(ipBuffer, ipBuffer.size)
    dnsTcpClient.receive(ipPacket)

    println("Solicitação DNS realizada com sucesso")

    val host = String(ipPacket.data, 0, ipPacket.length)
    val lista = host.split(":")
    val enderecoIP = lista[1]
    val porta = lista[2].toInt()
    println("Endereço e porta do servidor: $enderecoIP:$porta")

    val clientSocket = Socket(enderecoIP, porta)


    println("Conexão realizada com sucesso\uD83D\uDFE2")
    println("")
    println("Realize a operação conforme o exemplo: 2 + 2")

    val outputStream = clientSocket.getOutputStream()
    val inputStream = clientSocket.getInputStream()

    for (equacao in equacoes) {
        val startTime = System.nanoTime()

        val valor = equacao

        outputStream.write(equacao.toByteArray())
        outputStream.flush()

        val responseBuffer = ByteArray(1024)
        val bytesRead = inputStream.read(responseBuffer)
        val response = String(responseBuffer, 0, bytesRead)

        val endTime = System.nanoTime()

        println("")
        println("O resultado da operação $valor é $response")
        val elapsedTime = (endTime - startTime).toDouble() / TimeUnit.SECONDS.toNanos(1)
        println("Tempo total: %.5f segundos".format(elapsedTime))
    }

    val servicoFim = "1".toByteArray()
    val dnsPacketFim = DatagramPacket(servicoFim, servicoFim.size, InetAddress.getByName("127.0.0.1"), 5000)
    dnsTcpClient.send(dnsPacketFim)
    println()
    print("Para encerrar a conexão, basta digitar \"fim\" ")
    val mensagem = readLine()?.toLowerCase() ?: ""
    outputStream.write(mensagem.toByteArray())
    outputStream.flush()

    dnsTcpClient.close()
    clientSocket.close()
}

fun main() {
    val equacoes = listOf("89 + 1", "10 * 2", "-100 - 23",  "5 ** 0", "40 / 0")
    clientTCP(equacoes)
}
