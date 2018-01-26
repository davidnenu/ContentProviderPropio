package com.example.dm2.contentproviderpropio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {
    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate =
            "CREATE TABLE Usuarios (idUsuario INTEGER PRIMARY KEY, nombre TEXT, email TEXT)";
    public MySQLiteHelper(Context contexto, String nombre,
                                CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla

        db.execSQL(sqlCreate);
        if (db != null) {
            //Insertamoa 5 usuarios de ejemplo

            for (int i = 1; i <= 5; i++) {
                //Generamos los datos
                int codigo = i;
                String nombre = "Usuario " + i;
                String email = "usuario" + i + "@usuarios.com";
                //Insertamos los datos en la tabla de Usuarios
                db.execSQL("INSERT INTO Usuarios (idUsuario, nombre, email) VALUES (" + codigo + " , '" + nombre + "','" + email + "')");
            }
            //Cerramos la base de datos
            //db.close();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
 /*NOTA: Por simplicidad del ejemplo aquí utilizamos directamente
 la opción de eliminar la tabla anterior y crearla de nuevo vacia
 con el nuevo formato.
 Sin embargo lo normal será que haya que migrar datos de la
 tabla antigua a la nueva, por lo que este método deberia
 ser más elaborado.
 */
        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        //Se crea la nueva version de la tabla
        db.execSQL(sqlCreate);
    }
}

