package www.udb.edu.sv.farmacias_desafio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HistorialComprasActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var comprasAdapter: CompraAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_compras)


        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewHistorial)
        recyclerView.layoutManager = LinearLayoutManager(this)
        comprasAdapter =
            CompraAdapter(mutableListOf())
        recyclerView.adapter = comprasAdapter


        cargarHistorialCompras()
    }

    private fun cargarHistorialCompras() {
        val userId = auth.currentUser?.uid

        if (userId != null) {
            val comprasRef =
                db.collection("Usuarios").document(userId).collection("HistorialCompras")

            comprasRef.get()
                .addOnSuccessListener { querySnapshot ->
                    val listaCompras = querySnapshot.documents.map { document ->

                        val medicamentosMap =
                            document.get("medicamentos") as? List<Map<String, Any>> ?: listOf()


                        val medicamentos = medicamentosMap.map { map ->
                            Medicamento(
                                nombre = map["nombre"] as String,
                                precio = (map["precio"] as Double) ?: 0.0
                            )
                        }

                    }

                }
        }
    }
    }