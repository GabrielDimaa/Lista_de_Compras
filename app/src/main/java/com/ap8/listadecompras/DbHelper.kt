package info.camposha.kotlinsqlite
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.clientesapp.*

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val sql="CREATE TABLE $TABLE_COMPRAS ($ID INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, $PRODUTO TEXT, $QUANTIDADE TEXT)"
        db.execSQL(sql)
        Log.e("LOG","Criando")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS" + DATABASE_NAME)
        onCreate(db)
    }
}