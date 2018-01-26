package com.example.dm2.contentproviderpropio;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView tv;
    UsuariosProvider up;
    Uri usuariosUri =UsuariosProvider.CONTENT_URI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.salida);




    }
public void ver(View v){

    String [] projection = new String[] {
            UsuariosProvider.Usuarios._ID,
            UsuariosProvider.Usuarios.COL_NOMBRE,
            UsuariosProvider.Usuarios.COL_EMAIL,
    };
    ContentResolver cr = getContentResolver();
        Cursor c = cr.query(usuariosUri,projection,null,null,null);
    if (c.moveToFirst()){
        String nombre;
        String id;
        String email;
        int colID = c.getColumnIndex(UsuariosProvider.Usuarios._ID);
        int colNombre = c.getColumnIndex(UsuariosProvider.Usuarios.COL_NOMBRE);
        int colEmail = c. getColumnIndex(UsuariosProvider.Usuarios.COL_EMAIL);
        tv.setText("");
        do {
            id = c.getString(colID);
            nombre = c.getString(colNombre);
            email = c.getString(colEmail);
            tv.append(id + " - "+ nombre +" - "+email + "\n" );
        } while (c.moveToNext());
    }

}

}
