package www.udb.edu.sv.farmacias_desafio

data class Compra(
    val fecha: String,
    val total: Double,
    val medicamentos: List<Map<String, Any>>
)
