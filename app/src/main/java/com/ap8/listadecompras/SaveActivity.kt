package com.ap8.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.clientesapp.Compras
import com.example.clientesapp.ComprasDao
import kotlinx.android.synthetic.main.activity_update.*

class SaveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save)

        btn_remove.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        btn_save.setOnClickListener(View.OnClickListener {
            val produto = Compras(null, edtProduto.text.toString(), edtQuantidade.text.toString())
            val comprasDao = ComprasDao(this)
            comprasDao.insertDb(produto)
            onBackPressed()
        })
    }

}
