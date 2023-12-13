package cajero

import java.util.*

data class UsuarioBanco(
    val nombre: String,
    val id: String,
    val contrasena: String,
    val cuentas: MutableList<CuentaBancaria> = mutableListOf()
)

data class CuentaBancaria(
    val titular: UsuarioBanco,
    var saldo: Double,
    val numeroCuenta: String
)

data class TransaccionBancaria(
    val tipo: String,
    val fecha: Date,
    val cuenta: CuentaBancaria,
    val monto: Double
)

class CajeroAutomatico {
    val usuariosBanco = mutableListOf<UsuarioBanco>()
    val historialTransacciones = mutableListOf<TransaccionBancaria>()

    fun consultarSaldo(cuenta: CuentaBancaria): Double {
        return cuenta.saldo
    }

    fun realizarRetiro(cuenta: CuentaBancaria, monto: Double) {
        if (cuenta.saldo >= monto) {
            cuenta.saldo -= monto
            historialTransacciones.add(TransaccionBancaria("Retiro", Date(), cuenta, monto))
        } else {
            println("Fondos insuficientes para realizar el retiro.")
        }
    }

    fun depositarDinero(cuenta: CuentaBancaria, monto: Double) {
        cuenta.saldo += monto
        historialTransacciones.add(TransaccionBancaria("Depósito", Date(), cuenta, monto))
    }
}

fun main() {
    val cajero = CajeroAutomatico()

    val usuarioBanco1 = UsuarioBanco("Carlos Gonzalez", "001", "pass123")
    val cuenta1 = CuentaBancaria(usuarioBanco1, 1000.0, "123456789")

    val usuarioBanco2 = UsuarioBanco("Ana Martinez", "002", "pass456")
    val cuenta2 = CuentaBancaria(usuarioBanco2, 500.0, "987654321")

    cajero.usuariosBanco.addAll(listOf(usuarioBanco1, usuarioBanco2))

    println("Bienvenido al Cajero Automático")
    println("1. Consultar saldo")
    println("2. Realizar retiro")
    println("3. Depositar dinero")
    println("4. Salir")

    var opcion: Int
    do {
        print("Selecciona una opción: ")
        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                println("Saldo actual: ${cajero.consultarSaldo(cuenta1)}")
            }
            2 -> {
                print("Ingrese la cantidad a retirar: ")
                val montoRetiro = readLine()?.toDoubleOrNull() ?: 0.0
                cajero.realizarRetiro(cuenta1, montoRetiro)
                println("Retiro exitoso. Saldo actual: ${cuenta1.saldo}")
            }
            3 -> {
                print("Ingrese la cantidad a depositar: ")
                val montoDeposito = readLine()?.toDoubleOrNull() ?: 0.0
                cajero.depositarDinero(cuenta1, montoDeposito)
                println("Depósito exitoso. Saldo actual: ${cuenta1.saldo}")
            }
            4 -> println("Saliendo...")
            else -> println("Opción no válida. Inténtalo de nuevo.")
        }
    } while (opcion != 4)
}