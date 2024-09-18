package www.udb.edu.sv.farmacias_desafio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MedicamentoAdapter(private val medicamentos: List<Medicamento>, private val onClick: (Medicamento) -> Unit) : RecyclerView.Adapter<MedicamentoAdapter.MedicamentoViewHolder>() {

    inner class MedicamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombre_medicamento)
        val precioTextView: TextView = itemView.findViewById(R.id.precio_medicamento)

        fun bind(medicamento: Medicamento) {
            nombreTextView.text = medicamento.nombre
            precioTextView.text = "$${medicamento.precio}"
            itemView.setOnClickListener { onClick(medicamento) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medicamento, parent, false)
        return MedicamentoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicamentoViewHolder, position: Int) {
        holder.bind(medicamentos[position])
    }

    override fun getItemCount() = medicamentos.size
}
