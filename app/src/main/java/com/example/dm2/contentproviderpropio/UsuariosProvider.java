package com.example.dm2.contentproviderpropio;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UsuariosProvider extends ContentProvider{

    private static final String uri =
            "content://com.example.dm2.contentproviderpropio/usuarios";
    public static final Uri CONTENT_URI= Uri.parse(uri);
    //Base de datos
    private MySQLiteHelper usdbh;
    private static final String BD_NOMBRE = "DBUsuarios";
    private static final int BD_VERSION = 1;
    private static final String TABLA_USUARIOS = "Usuarios";
    //Necesario para UriMatcher
    private static final int USUARIOS = 1;
    private static final int USUARIOS_ID = 2;
    private static final UriMatcher uriMatcher;
    //Inicializamos el UriMatcher
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.dm2.contentproviderpropio",
                "usuarios", USUARIOS);
        uriMatcher.addURI("com.example.dm2.contentproviderpropio",
                "usuarios/#", USUARIOS_ID);
    }
    //Clase interna para declarar las constantes de columna
    public static final class Usuarios implements BaseColumns {
        private Usuarios () {}
        //Nombres de columnas
        public static final String _ID = "idUsuario";
        public static final String COL_NOMBRE = "nombre";
        public static final String COL_EMAIL = "email";
    }
    private SQLiteDatabase db;


    public boolean onCreate() {
        usdbh = new MySQLiteHelper(this.getContext(), BD_NOMBRE, null, BD_VERSION);
        //db = usdbh.getWritableDatabase();
       return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if (uriMatcher.match(uri)== USUARIOS_ID) {
            where ="_id=" + uri.getLastPathSegment();
        }

        db = usdbh.getWritableDatabase();
        Cursor c = db.query(TABLA_USUARIOS, projection, where,
                selectionArgs, null, null,sortOrder);
        return c;
    }


    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match){
            case USUARIOS:
                return "vnd.android.cursor.dir/vnd.ejemplo.usuario";
            case USUARIOS_ID:
                return "vnd.android.cursor.item/vnd.ejemplo.usuario";
            default:
                return null;
        }
    }



    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int cont;
        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if (uriMatcher.match(uri)== USUARIOS_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        db = usdbh.getWritableDatabase();
        cont = db.update(TABLA_USUARIOS, values, where, selectionArgs);
        return cont;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int cont;
        //Si es una consulta a un ID concreto construimos el WHERE
        String where = selection;
        if (uriMatcher.match(uri) == USUARIOS_ID) {
            where = "_id="+ uri.getLastPathSegment();
        }
        db = usdbh.getWritableDatabase();
        cont = db.delete(TABLA_USUARIOS, where,selectionArgs);
        return cont;
    }



}
