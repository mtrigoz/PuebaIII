package www.runa.puebaiii;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexUsr extends SQLiteOpenHelper {
    public static final String DBNAME  = "db_PostCuarentena";
    public ConexUsr(Context context) {
        super(context, "db_PostCuarentena", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MiDB) {
        MiDB.execSQL("create Table PostCuarentena(Usuario TEXT primary key, password TEXT, Sexo TEXT, NomCompleto TXT,ApllCompleto TXT)");
   }

    @Override
    public void onUpgrade(SQLiteDatabase MiDB, int i, int i1) {
        MiDB.execSQL("drop Table if exists PostCuarentena");
    }


    public Boolean insertData(String Usuario, String password, String Sexo, String NomCompleto, String ApllCompleto ){
        SQLiteDatabase MiDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("Usuario", Usuario);
        contentValues.put("password", password);
        contentValues.put("Sexo", Sexo);
        contentValues.put("NomCompleto", NomCompleto);
        contentValues.put("ApllCompleto", ApllCompleto);
        long result = MiDB.insert("PostCuarentena", null, contentValues);
        return result != -1;
    }

    public Boolean checkusername(String Usuario) {
        SQLiteDatabase MiDB = this.getWritableDatabase();
        Cursor cursor = MiDB.rawQuery("Select * from PostCuarentena where Usuario = ?", new String[]{Usuario});
        return cursor.getCount() > 0;
    }

    public Boolean checkSexo(String Sexo) {
        SQLiteDatabase MiDB = this.getWritableDatabase();
        Cursor cursor = MiDB.rawQuery("Select * from PostCuarentena where Sexo = ?", new String[]{Sexo});
        return cursor.getCount() > 0;
    }

    public Boolean checkNomCompleto(String NomCompleto) {
        SQLiteDatabase MiDB = this.getWritableDatabase();
        Cursor cursor = MiDB.rawQuery("Select * from PostCuarentena where NomCompleto = ?", new String[]{NomCompleto});
        return cursor.getCount() > 0;
    }

    public Boolean checkApllCompleto(String ApllCompleto) {
        SQLiteDatabase MiDB = this.getWritableDatabase();
        Cursor cursor = MiDB.rawQuery("Select * from PostCuarentena where ApllCompleto = ?", new String[]{ApllCompleto});
        return cursor.getCount() > 0;
    }

    public Boolean checkusernamepassword(String Usuario, String password){
        SQLiteDatabase MiDB = this.getWritableDatabase();
        Cursor cursor = MiDB.rawQuery("Select * from PostCuarentena where Usuario = ? and password = ?", new String[] {Usuario,password});
        return cursor.getCount() > 0;
    }


}
