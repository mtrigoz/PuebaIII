package www.runa.puebaiii;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexFom  extends SQLiteOpenHelper {

    public static final String db_formulario="formulario.db";
    public static final String table_formulario="formulario_table";

    public String col1  = "id";
    public String col2 = "LugarV";
    public String col3 = "Longitud";
    public String col4 = "Latitud";


    public ConexFom(@Nullable Context context){
        super(context, db_formulario, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+table_formulario+"(ID INTEGER PRIMARY KEY, LUGARV TEXT, LONGITUD TEXT, LATITUD TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    ///////////////////////////////
    public  boolean AgregarDatos(String LugarV,String Longitud,String Latitud) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2, LugarV);
        contentValues.put(col3, Longitud);
        contentValues.put(col4, Latitud);

        long resultado = db.insert(table_formulario, null, contentValues);

        if (resultado == -1) {
            return false;
        } else {
            return true;
        }
    }

    //////////////
    public Cursor verDatos(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor respuesta = database.rawQuery("select * from "+table_formulario,null);
        return respuesta;
    }

    ///////////////////////////////////////
    public  Integer EliminarDatos(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return  database.delete(table_formulario,"id = ?", new String[]{id});
    }
    ///////////////////////////////////////
    public boolean ActualizarDatos(String id, String Longitud,String Latitud){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,id);
        contentValues.put(col2, Longitud);
        contentValues.put(col3, Latitud);

        sqLiteDatabase.update(table_formulario,contentValues,"id = ?", new String[]{id});
        return true;
    }

}
