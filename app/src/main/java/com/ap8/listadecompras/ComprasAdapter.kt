package com.ap8.listadecompras

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clientesapp.Compras
import kotlinx.android.synthetic.main.activity_compra.view.*

class ComprasAdapter (val compras: List<Compras>):
    RecyclerView.Adapter<ComprasAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComprasAdapter.VH {
        val v = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_compra,parent,false)

        val vh = VH(v)

        vh.itemView.setOnClickListener{
            val produto= compras[vh.adapterPosition]
            val intencao = Intent(parent.context, UpdateActivity::class.java)
            intencao.putExtra("compra", produto)
            parent.context.startActivity(intencao)
        }

        return vh
    }

    override fun getItemCount(): Int {
        return compras.size
    }

    override fun onBindViewHolder(holder: ComprasAdapter.VH, position: Int) {
        val compras = compras[position]
        holder.viewProduto.text = compras.produto.toString()
        holder.viewQuantidade.text = compras.quantidade.toString()
    }

    class VH(item: View): RecyclerView.ViewHolder(item) {
        var viewProduto: TextView = item.viewProduto
        var viewQuantidade: TextView = item.viewQuantidade
    }
}
