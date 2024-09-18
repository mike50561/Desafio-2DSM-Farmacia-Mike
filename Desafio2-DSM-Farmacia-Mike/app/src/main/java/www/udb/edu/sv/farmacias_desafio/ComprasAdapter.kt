package www.udb.edu.sv.farmacias_desafio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import www.udb.edu.sv.farmacias_desafio.Compra


class CompraAdapter(private var compras: MutableList<Compra>) : RecyclerView.Adapter<CompraAdapter.CompraViewHolder>() {

    class CompraViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val fechaTextView: TextView = view.findViewById(R.id.fecha_compra)
        val totalTextView: TextView = view.findViewById(R.id.total_compra)
        val medicamentosTextView: TextView = view.findViewById(R.id.recyclerViewHistorial)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_compra, parent, false)
        return CompraViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompraViewHolder, position: Int) {
        val compra = compras[position]
        holder.fechaTextView.text = "Fecha: ${compra.fecha}"
        holder.totalTextView.text = "Total: $${compra.total}"
        holder.medicamentosTextView.text = "Medicamentos: ${compra.medicamentos.joinToString(", ")}"
    }

    override fun getItemCount(): Int {
        return compras.size
    }


    fun updateData(nuevasCompras: List<Compra>) {
        compras.clear()
        compras.addAll(nuevasCompras)
        notifyDataSetChanged()
    }
}
