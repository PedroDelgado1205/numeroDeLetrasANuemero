fun main() {
    print("Ingrese un número en palabras (por ejemplo, 'cinco'): ")
    val entrada = readLine()

    if (entrada != null) {
        val numero = convertirTextoANumero(entrada)

        if (numero != -1) {
            println(numero)
        } else {
            println("Entrada no válida")
        }
    } else {
        println("Error al leer la entrada")
    }
}

fun convertirTextoANumero(texto: String): Int {
    val conversiones = listOf(
        "cero",
        "uno",
        "dos",
        "tres",
        "cuatro",
        "cinco",
        "seis",
        "siete",
        "ocho",
        "nueve",
        "diez"
    )

    val indice = conversiones.indexOf(texto.toLowerCase())

    return indice;
}
