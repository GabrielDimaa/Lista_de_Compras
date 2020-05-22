package com.example.clientesapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import info.camposha.kotlinsqlite.DbHelper


class ComprasDao(context: Context) {
    var banco = DbHelper(context)

    fun insertDb(compras: Compras): String {
        val db = banco.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(PRODUTO, compras.produto)
        contentValues.put(QUANTIDADE, compras.quantidade)
        val resp_id = db.insert(TABLE_COMPRAS, null, contentValues)
        val msg = if(resp_id != -1L) {
            "Inserido com sucesso"
        } else {
            "Erro ao inserir"
        }
        db.close()

        return msg
    }

    fun updateDb(compras: Compras): Boolean {
        val db = banco.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, compras.id)
        contentValues.put(PRODUTO, compras.produto)
        contentValues.put(QUANTIDADE, compras.quantidade)
        db.insertWithOnConflict(TABLE_COMPRAS,null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)

        db.close()
        return true
    }

    fun removeDb(compras: Compras) : Int {
        val db = banco.writableDatabase
        return db.delete(TABLE_COMPRAS,"ID =?", arrayOf(compras.id.toString()))
    }

    fun getAll(): ArrayList<Compras> {
        Log.v("LOG", "GetAll")
        val db = banco.writableDatabase
        val sql = "SELECT * from ${TABLE_COMPRAS}"
        val cursor = db.rawQuery(sql ,null)
        val compras = ArrayList<Compras>()
        while(cursor.moveToNext()){
            val produto = produtoFromCursor(cursor)
            compras.add(produto)
        }

        cursor.close()
        db.close()
        Log.v("LOG", "Get ${compras.size}")
        return compras
    }


    fun getByProduto(produto: String): ArrayList<Compras> {
        val db = banco.writableDatabase
        val sql = "SELECT * from ${TABLE_COMPRAS} WHERE ${PRODUTO} LIKE '%$produto%'"
        val cursor = db.rawQuery(sql ,null)
        val compras = ArrayList<Compras>()
        while(cursor.moveToNext()) {
            val produto_ = produtoFromCursor(cursor)
            compras.add(produto_)
        }

        cursor.close()
        db.close()
        return compras
    }

    private fun produtoFromCursor(cursor: Cursor): Compras{
        val id = cursor.getInt(cursor.getColumnIndex(ID))
        val produto = cursor.getString(cursor.getColumnIndex(PRODUTO))
        val quantidade = cursor.getString(cursor.getColumnIndex(QUANTIDADE))
        return Compras(id, produto, quantidade)
    }
}