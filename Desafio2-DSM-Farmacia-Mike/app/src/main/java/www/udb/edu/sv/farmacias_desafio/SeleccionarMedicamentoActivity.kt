package www.udb.edu.sv.farmacias_desafio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SeleccionarMedicamentoActivity : AppCompatActivity() {

    private val medicamentosSeleccionados = mutableListOf<Medicamento>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_medicamento)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_medicamentos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val medicamentos = MedicamentosData.getMedicamentos()

        val adapter = MedicamentoAdapter(medicamentos) { medicamento ->
            // Añadir el medicamento a la lista seleccionada
            medicamentosSeleccionados.add(medicamento)
            Toast.makeText(this, "${medicamento.nombre} añadido a la orden", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter

        // Botón para ver la orden
        val verOrdenButton = findViewById<Button>(R.id.ver_orden_button)
        verOrdenButton.setOnClickListener {
            // Crear el intent para ir a la pantalla de mostrar orden
            val intent = Intent(this, MostrarOrdenActivity::class.java)
            intent.putParcelableArrayListExtra("medicamentosSeleccionados", ArrayList(medicamentosSeleccionados))
            startActivity(intent)
        }
    }
}
