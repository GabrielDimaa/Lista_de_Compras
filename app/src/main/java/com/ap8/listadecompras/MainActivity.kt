package com.ap8.listadecompras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.clientesapp.Compras
import com.example.clientesapp.ComprasDao
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var listaCompras = mutableListOf<Compras>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateAdapter()
        btn_add.setOnClickListener(View.OnClickListener {
            val intencao = Intent(this, SaveActivity::class.java)
            startActivity(intencao)
        })
        initRecyclerView()

        btn_filter.setOnClickListener {
            findAdapter(edtSearch.text.toString())
        }
    }

    override fun onRestart() {
        super.onRestart()
        updateAdapter()
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
        initRecyclerView()
    }

    private fun updateAdapter() {
        val comprasDao = ComprasDao(this)
        listaCompras.clear()
        listaCompras = comprasDao.getAll()
        if(listaCompras.isEmpty()) {
            rv.setVisibility(View.GONE);
            view_msg.setVisibility(View.VISIBLE);
            view_msg.setText("Sem dados para exibir")
        } else {
            rv.setVisibility(View.VISIBLE);
            view_msg.setVisibility(View.GONE);
        }
        rv.adapter?.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        val adapter_ = ComprasAdapter(listaCompras)
        rv.adapter = adapter_
        val layout = GridLayoutManager(this, 2)
        rv.layoutManager = layout
    }

    private fun findAdapter(produto: String) {
        val comprasDao = ComprasDao(this)
        listaCompras.clear()
        listaCompras = comprasDao.getByProduto(produto)
        if (listaCompras.isEmpty()) {
            rv.setVisibility(View.GONE)
            view_msg.setVisibility(View.VISIBLE)
            view_msg.setText("Nenhum ${produto} encontrado")
        }
        else {
            rv.setVisibility(View.VISIBLE)
            view_msg.setVisibility(View.GONE)
            initRecyclerView()
        }
        rv.adapter?.notifyDataSetChanged()
    }
}
