import java.net.DatagramPacket
import java.net.DatagramSocket
import java.util.concurrent.ConcurrentHashMap
import kotlin.concurrent.thread

fun main() {
    val serverIp = "127.0.0.1"
    val serverPort = 1025

    val serverSocket = DatagramSocket(serverPort)
    println("Servidor de Registro de Nomes aguardando conexões em $serverIp:$serverPort")
    println("")

    val services = ConcurrentHashMap<String, Pair<String, String>>()

    thread {
        while (true) {
            val buffer = ByteArray(1024)
            val packet = DatagramPacket(buffer, buffer.size)
            serverSocket.receive(packet)
            val clientAddress = packet.address
            val clientPort = packet.port
            val data = String(packet.data, 0, packet.length)

            println("Conexão estabelecida com $clientAddress:$clientPort")
            println("")

            val response: String = when (data) {
                "servidorTCP" -> {
                    getServiceResponse("servidorTCP", services)
                }

                "servidorUDP" -> {
                    getServiceResponse("servidorUDP", services)
                }

                else -> {
                    val serviceInfo = data.split(" ")
                    if (serviceInfo.size >= 2) {
                        val action = serviceInfo[0]
                        val serviceName = serviceInfo[1]

                        when (action) {
                            "register" -> {
                                if (serviceInfo.size == 4) {
                                    val protocol = serviceInfo[2]
                                    val port = serviceInfo[3]
                                    services[serviceName] = Pair(protocol, port)
                                    "Aplicacao registrada: $serviceName ($protocol:$port)"
                                } else {
                                    "Registro inválido"
                                }
                            }

                            "Consulta" -> {
                                getServiceResponse(serviceName, services)
                            }

                            "deletar" -> {
                                if (services.containsKey(serviceName)) {
                                    services.remove(serviceName)
                                    println("")
                                    println("Os servidores UDP e TCP foram removidos")

                                    println("")
                                }
                                ""
                            }

                            else -> "Comando inválido"
                        }
                    } else {
                        "Comando inválido"
                    }
                }
            }

            val responseData = response.toByteArray()
            val responsePacket = DatagramPacket(responseData, responseData.size, clientAddress, clientPort)
            serverSocket.send(responsePacket)
        }
    }

    try {
        while (true) {
            Thread.sleep(1000)
        }
    } catch (ex: InterruptedException) {
    } finally {
        serverSocket.close()
    }
}

private fun getServiceResponse(serviceName: String, services: ConcurrentHashMap<String, Pair<String, String>>): String {
    return if (services.containsKey(serviceName)) {
        val (protocol, port) = services[serviceName]!!
        "$serviceName:$protocol:$port"
    } else {
        "Serviço não encontrado: $serviceName"
    }
}
