package www.udb.edu.sv.farmacias_desafio

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MostrarOrdenActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var medicamentosSeleccionados: MutableList<Medicamento>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_orden)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        // Obtener la lista de medicamentos seleccionados desde el intent
        medicamentosSeleccionados = intent.getParcelableArrayListExtra("medicamentosSeleccionados") ?: mutableListOf()

        // Configurar el RecyclerView para mostrar los medicamentos seleccionados
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_orden)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val ordenAdapter = OrdenAdapter(medicamentosSeleccionados)
        recyclerView.adapter = ordenAdapter

        // Calcular el total
        val total = medicamentosSeleccionados.sumByDouble { it.precio }

        // Mostrar el total
        val totalTextView = findViewById<TextView>(R.id.total_orden)
        totalTextView.text = "Total: $$total"

        // Configurar el botón de confirmar compra
        val confirmarCompraButton = findViewById<Button>(R.id.confirmar_orden_button)
        confirmarCompraButton.setOnClickListener {
            confirmarCompra()
        }

        // Botón para cancelar la orden
        val cancelarButton = findViewById<Button>(R.id.cancelar_orden_button)
        cancelarButton.setOnClickListener {
            cancelarOrden()
        }
    }

    private fun confirmarCompra() {
        medicamentosSeleccionados.clear()

        // Regresar a la lista de medicamentos
        val intent = Intent(this, GraciasCompraActivity::class.java)
        startActivity(intent)
        finish()

    }


    private fun cancelarOrden() {
        medicamentosSeleccionados.clear()


        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun mostrarNotificacion(mensaje: String) {
        val channelId = "compra_channel"
        val channelName = "Compra Notifications"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Confirmación de Compra")
            .setContentText(mensaje)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.notify(1, notificationBuilder.build())
    }
}
