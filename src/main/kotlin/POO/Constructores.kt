package POO

class Persona (){
    var name : String = ""
    var age : Int = 0
}

fun main(){
    var p1 : Persona = Persona()
    p1.name = "Pedro"
    p1.age = 21
    println("""
        Nombre: ${p1.name}
        Edad: ${p1.age}
        """.trimIndent())
}