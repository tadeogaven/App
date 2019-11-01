package com.example.healthme;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Connection;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearReceta extends AppCompatActivity {

    EditText nombre1,descripcion1,tiempo1;
    Context ctx=this;
    Recetas rec = new Recetas();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearreceta);
        descripcion1=findViewById(R.id.descripcion);
        nombre1=findViewById(R.id.nombrereceta);
        tiempo1=findViewById(R.id.duracion);

        String nombre, descripcion,tiempo;

        nombre=nombre1.getText().toString();
        descripcion=descripcion1.getText().toString();
        tiempo=tiempo1.getText().toString();

        rec._nombre=nombre;
        rec._tiempo=tiempo;
        rec._descripción=descripcion;
    }

    public void Enviar(View enviar) {

         class tareaAsincronicaRecetas extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    String MiPc = "10.0.2.2:50197";
                    URL Ruta = new URL("http://" + MiPc + "/api/InsertarReceta");

                    HttpURLConnection urlConnection = (HttpURLConnection) Ruta.openConnection();
                    OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

                    Gson gson = new Gson();
                    String json = gson.toJson(rec);

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                    writer.write(json);
                    writer.flush();
                    writer.close();
                    out.close();

                    urlConnection.connect();
                } catch (Exception error) {
                    Log.d("error", "es" + error.getMessage());
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }


        }

        Intent intent = new Intent(CrearReceta.this, Home.class);
        startActivity(intent);
    }




}


