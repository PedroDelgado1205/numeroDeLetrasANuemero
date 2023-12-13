package libreria

import java.util.*

data class Libro(
    val titulo: String,
    val autor: String,
    val isbn: String,
    var cantidadDisponible: Int,
    val fechaAdquisicion: Date,
    val genero: String
)

data class Usuario(
    val nombre: String,
    val id: String,
    val direccion: String,
    val librosPrestados: MutableList<Libro> = mutableListOf()
)

data class Transaccion(
    val tipo: String,
    val fecha: Date,
    val libro: Libro,
    val usuario: Usuario
)

class Biblioteca {
    val inventarioLibros = mutableListOf<Libro>()
    val usuarios = mutableListOf<Usuario>()
    val historialTransacciones = mutableListOf<Transaccion>()

    fun prestarLibro(libro: Libro, usuario: Usuario) {
        // Lógica para prestar un libro
        if (libro.cantidadDisponible > 0) {
            libro.cantidadDisponible--
            usuario.librosPrestados.add(libro)
            historialTransacciones.add(Transaccion("Préstamo", Date(), libro, usuario))
        } else {
            println("No hay copias disponibles de ${libro.titulo}.")
        }
    }

    fun devolverLibro(libro: Libro, usuario: Usuario) {
        // Lógica para devolver un libro
        if (usuario.librosPrestados.contains(libro)) {
            libro.cantidadDisponible++
            usuario.librosPrestados.remove(libro)
            historialTransacciones.add(Transaccion("Devolución", Date(), libro, usuario))
        } else {
            println("El usuario no tiene prestado ${libro.titulo}.")
        }
    }
}

fun main() {
    val biblioteca = Biblioteca()

    val libro1 = Libro("Harry Potter", "J.K. Rowling", "978-0545162074", 5, Date(), "Fantasía")
    val libro2 = Libro("1984", "George Orwell", "978-0451524935", 3, Date(), "Distopía")

    biblioteca.inventarioLibros.addAll(listOf(libro1, libro2))

    val usuario1 = Usuario("Juan Perez", "123456", "Calle 123")
    val usuario2 = Usuario("Maria Rodriguez", "789012", "Avenida 456")

    biblioteca.usuarios.addAll(listOf(usuario1, usuario2))

    println("Bienvenido a la Biblioteca")
    println("1. Prestar libro")
    println("2. Devolver libro")
    println("3. Salir")

    var opcion: Int
    do {
        print("Selecciona una opción: ")
        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                println("Libros disponibles:")
                biblioteca.inventarioLibros.forEachIndexed { index, libro ->
                    println("${index + 1}. ${libro.titulo} de ${libro.autor}")
                }

                print("Selecciona el número de libro a prestar: ")
                val libroSeleccionado = readLine()?.toIntOrNull()?.let {
                    if (it in 1..biblioteca.inventarioLibros.size) {
                        biblioteca.inventarioLibros[it - 1]
                    } else {
                        null
                    }
                }

                if (libroSeleccionado != null) {
                    biblioteca.prestarLibro(libroSeleccionado, usuario1)
                    println("Libro prestado con éxito.")
                } else {
                    println("Selección de libro no válida.")
                }
            }
            2 -> {
                println("Libros prestados:")
                usuario1.librosPrestados.forEachIndexed { index, libro ->
                    println("${index + 1}. ${libro.titulo} de ${libro.autor}")
                }

                print("Selecciona el número de libro a devolver: ")
                val libroSeleccionado = readLine()?.toIntOrNull()?.let {
                    if (it in 1..usuario1.librosPrestados.size) {
                        usuario1.librosPrestados[it - 1]
                    } else {
                        null
                    }
                }

                if (libroSeleccionado != null) {
                    biblioteca.devolverLibro(libroSeleccionado, usuario1)
                    println("Libro devuelto con éxito.")
                } else {
                    println("Selección de libro no válida.")
                }
            }
            3 -> println("Saliendo...")
            else -> println("Opción no válida. Inténtalo de nuevo.")
        }

    } while (opcion != 3)
}