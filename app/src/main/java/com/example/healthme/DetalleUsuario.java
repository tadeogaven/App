package com.example.healthme;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DetalleUsuario extends AppCompatActivity {
    ListView ListaObjetosUsuarios;
    ArrayAdapter miAdaptador;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_usuario);
        Bundle datosRecibidos = this.getIntent().getExtras();
        usuario = (Usuario)datosRecibidos.getSerializable("usuario");
        ((TextView)findViewById(R.id.id)).setText(""+usuario.getId());
        ((TextView)findViewById(R.id.nombre)).setText(usuario.getNombre());
        ((TextView)findViewById(R.id.apellido)).setText(usuario.getApellido());
        ((TextView)findViewById(R.id.email)).setText(usuario.getEmail());
    }
}
