package com.ap8.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clientesapp.Compras
import com.example.clientesapp.ComprasDao
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val produto = intent.getParcelableExtra<Compras>("compra")

        edtProduto.setText(produto.produto.toString())
        edtQuantidade.setText(produto.quantidade.toString())

        btn_remove.setOnClickListener {
            val comprasDao = ComprasDao(this)
            comprasDao.removeDb(produto)
            onBackPressed()
        }

        btn_save.setOnClickListener {
            val produto = Compras(produto.id, edtProduto.text.toString(), edtQuantidade.text.toString())
            val comprasDao = ComprasDao(this)
            comprasDao.updateDb(produto)
            onBackPressed()
        }
    }
}
