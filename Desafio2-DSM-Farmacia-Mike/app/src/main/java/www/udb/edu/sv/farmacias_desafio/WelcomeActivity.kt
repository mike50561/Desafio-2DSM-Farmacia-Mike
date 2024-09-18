package www.udb.edu.sv.farmacias_desafio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        auth = FirebaseAuth.getInstance()


        val verListaMedicamentosButton = findViewById<Button>(R.id.ver_lista_medicamentos_button)
        verListaMedicamentosButton.setOnClickListener {
            val intent = Intent(this, SeleccionarMedicamentoActivity::class.java)
            startActivity(intent)
        }

        val historialComprasButton = findViewById<Button>(R.id.historial_compras_button)
        historialComprasButton.setOnClickListener {
            val intent = Intent(this, HistorialComprasActivity::class.java)
            startActivity(intent)
        }



        val cerrarSesionButton = findViewById<Button>(R.id.cerrar_sesion_button)
        cerrarSesionButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}
