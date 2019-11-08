package com.example.healthme;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Connection;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CrearReceta extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText nombre1,descripcion1,tiempo1,imagen1;
    Spinner dificultad1;
    Context ctx=this;
    Recetas rec = new Recetas();
    String[] dificultad = {"Fácil","Medio","Dificil","Muy Dificil"};
    String[] dieta = {"Vegetariana","Vegana","Carnivora"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearreceta);
        descripcion1=findViewById(R.id.descripcion);
        nombre1=findViewById(R.id.nombrereceta);
        tiempo1=findViewById(R.id.duracion);
        imagen1=findViewById(R.id.imagenreceta);
        dificultad1=findViewById(R.id.spinner);


        Spinner spinnerDificultad = (Spinner) findViewById(R.id.spinner);
        spinnerDificultad.setOnItemSelectedListener(this);
        Spinner spinnerDieta = (Spinner) findViewById(R.id.spinnerDieta);
        spinnerDieta.setOnItemSelectedListener(this);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, dificultad);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDificultad.setAdapter(aa);
        spinnerDificultad.setOnItemSelectedListener(this);
        ArrayAdapter<String> aad = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, dieta);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDieta.setAdapter(aad);
        spinnerDieta.setOnItemSelectedListener(this);






        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dificultad);
        ArrayAdapter<String> dataAdapterDieta = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dieta);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterDieta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerDificultad.setAdapter(dataAdapter);
        spinnerDieta.setAdapter(dataAdapterDieta);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id ) {
        Spinner spin = (Spinner) parent;
        Spinner spin2 = (Spinner) parent;
        if (spin.getId() == R.id.spinner) {
            Toast.makeText(this, "Elegiste :" + dificultad[position], Toast.LENGTH_SHORT).show();
            Dificultad.fromString(dificultad[position]).getId();
        }
        if (spin2.getId() == R.id.spinnerDieta) {
            Toast.makeText(this, "Elegiste :" + dieta[position], Toast.LENGTH_SHORT).show();
           Dificultad.fromString(dieta[position]).getId();

        }

    }
    public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(this, "Seleccion :", Toast.LENGTH_SHORT).show();
    }






    public void Enviar(View enviar) {
        String nombre, descripcion,tiempo,imagen;
        nombre=nombre1.getText().toString();
        descripcion=descripcion1.getText().toString();
        tiempo=tiempo1.getText().toString();
        imagen=imagen1.getText().toString();
        String difi= ""+dificultad1.getSelectedItemPosition();


        rec._IdDificiltad=difi;
        rec._imagen=imagen;
        rec._nombre=nombre;
        rec._tiempo=tiempo;
        rec._descripción=descripcion;
         class tareaAsincronicaRecetas extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    String MiPc = "10.0.2.2:50197";
                    URL Ruta = new URL("http://" + MiPc + "/api/InsertarReceta");
                    HttpURLConnection conn = (HttpURLConnection) Ruta.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    conn.setRequestProperty("Charset", "UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    JSONObject json = new JSONObject();

                    json.put("Nombre", rec._nombre);
                    json.put("Imagen", rec._imagen);
                    json.put("Tiempo", rec._tiempo);
                    json.put("Descripcion", rec._descripción);
                    json.put("Dificultad", rec._IdDificiltad);
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                    Log.d("DatosNuevos",rec._nombre);
                    Log.i("JSON", json.toString());
                    os.flush();
                    os.close();
                    conn.disconnect();
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

        tareaAsincronicaRecetas tarea = new tareaAsincronicaRecetas();
        tarea.execute();

        Intent intent = new Intent(CrearReceta.this,Home.class);
        startActivity(intent);
    }




}


