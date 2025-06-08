package com.github.alunos_rm98342_rm551268

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventoExtremoAdapter(
    private val eventos: MutableList<EventoExtremo>,
    private val aoExcluir: (Int) -> Unit
) : RecyclerView.Adapter<EventoExtremoAdapter.EventoExtremoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoExtremoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_evento_extremo, parent, false)
        return EventoExtremoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventoExtremoViewHolder, position: Int) {
        val evento = eventos[position]
        holder.textLocal.text = "Local: ${evento.local}"
        holder.textTipoEvento.text = "Evento: ${evento.tipoEvento}"
        holder.textGrauImpacto.text = "Impacto: ${evento.grauImpacto}"
        holder.textData.text = "Data: ${evento.data}"
        holder.textPessoasAfetadas.text = "Afetados: ${evento.pessoasAfetadas}"
        holder.botaoExcluir.setOnClickListener { aoExcluir(position) }
    }

    override fun getItemCount() = eventos.size

    class EventoExtremoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textLocal: TextView = itemView.findViewById(R.id.txtLocal)
        val textTipoEvento: TextView = itemView.findViewById(R.id.txtTipoEvento)
        val textGrauImpacto: TextView = itemView.findViewById(R.id.txtGrauImpacto)
        val textData: TextView = itemView.findViewById(R.id.txtData)
        val textPessoasAfetadas: TextView = itemView.findViewById(R.id.txtPessoasAfetadas)
        val botaoExcluir: Button = itemView.findViewById(R.id.botaoExcluir)
    }
}