package com.github.alunos_rm98342_rm551268

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var eventoAdapter: EventoExtremoAdapter
    private val listaEventos = mutableListOf<EventoExtremo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editLocal = findViewById<EditText>(R.id.Local)
        val editTipoEvento = findViewById<EditText>(R.id.TipoEvento)
        val editGrauImpacto = findViewById<EditText>(R.id.GrauImpacto)
        val editData = findViewById<EditText>(R.id.Data)
        val editPessoasAfetadas = findViewById<EditText>(R.id.PessoasAfetadas)
        val botaoIncluir = findViewById<android.widget.Button>(R.id.botaoIncluir)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerEventos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        eventoAdapter = EventoExtremoAdapter(listaEventos) { posicao ->
            listaEventos.removeAt(posicao)
            eventoAdapter.notifyItemRemoved(posicao)
        }
        recyclerView.adapter = eventoAdapter

        botaoIncluir.setOnClickListener {
            val local = editLocal.text.toString().trim()
            val tipoEvento = editTipoEvento.text.toString().trim()
            val grauImpacto = editGrauImpacto.text.toString().trim()
            val data = editData.text.toString().trim()
            val pessoasAfetadasStr = editPessoasAfetadas.text.toString().trim()

            if (local.isEmpty() || tipoEvento.isEmpty() ||
                grauImpacto.isEmpty() || data.isEmpty() || pessoasAfetadasStr.isEmpty()) {
                Toast.makeText(this, "Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pessoasAfetadas = pessoasAfetadasStr.toIntOrNull()
            if (pessoasAfetadas == null || pessoasAfetadas <= 0) {
                Toast.makeText(this, "O número de pessoas afetadas deve ser maior que zero!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val evento = EventoExtremo(local, tipoEvento, grauImpacto, data, pessoasAfetadas)
            listaEventos.add(evento)
            eventoAdapter.notifyItemInserted(listaEventos.size - 1)

            // Limpa os campos
            editLocal.text.clear()
            editTipoEvento.text.clear()
            editGrauImpacto.text.clear()
            editData.text.clear()
            editPessoasAfetadas.text.clear()
        }

        findViewById<android.widget.Button>(R.id.botaoSobre).setOnClickListener {
            startActivity(Intent(this, SobreActivity::class.java))
        }
    }
}