package com.example.examen_segundoparcial;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.security.PublicKey;

public class CRUDRECETAS extends SQLiteOpenHelper {
    Context context;
    private static final String BD_NOMBRE = "myDatabase.db";
    private static final int BD_VERSION = 1;
    private static final String TABLA_SQL = "CREATE TABLE recetas " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, descripcion TEXT)";

    public CRUDRECETAS(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public CRUDRECETAS(Context context) {
        super(context, BD_NOMBRE, null, BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(TABLA_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {

    }

    public void insertarReceta(String titulo, String descripcion) {
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues registros = new ContentValues();
        registros.put("titulo", titulo);
        registros.put("descripcion", descripcion);
        bd.insert("recetas", null, registros);
    }

    public Cursor mostrarRecetas() {
        SQLiteDatabase bd = this.getReadableDatabase();
        String consultaSQL = "SELECT * FROM recetas";
        Cursor listaRegistros = bd.rawQuery(consultaSQL, null);
        return listaRegistros;
    }

    public void actualizarReceta(int id, String titulo, String descripcion) {
        SQLiteDatabase bd = this.getWritableDatabase();
        ContentValues registros = new ContentValues();
        registros.put("titulo", titulo);
        registros.put("descripcion", descripcion);
        bd.update("recetas", registros, "id=?", new String[]{String.valueOf(id)});
    }

    public void eliminarReceta(int id) {
        SQLiteDatabase bd = this.getWritableDatabase();
        bd.delete("recetas", "id=?", new String[]{String.valueOf(id)});
    }

}
