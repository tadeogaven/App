package com.example.healthme;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.FormatFlagsConversionMismatchException;

public  class VerPerfil extends AppCompatActivity {
    private String Mail;
    private String nombreUsuario;
    private String apellido;
    private String nombre;
    private TextView nombre1;
    private TextView usuario1;
    private TextView apellido1;
    TextView mail1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("gdfs", "entra");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_perfil);
        usuario1= findViewById(R.id.NombreUsuario);
        nombre1=findViewById(R.id.Nombre);
        apellido1=findViewById(R.id.Apellido);
        mail1=findViewById(R.id.Mail);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle.toString() != ""){
            Log.d("gdfs", bundle.getString("Mail").toString());
            Mail = bundle.getString("Mail").toString();
            Log.d("MailPerfil", Mail.toString());
            //Log.d("NombreUsuario", nombreUsuario);

        }
        tareaAsincronicaUsuario buscarUsuariosPorMail = new tareaAsincronicaUsuario();
        buscarUsuariosPorMail.execute();

    }

    public class tareaAsincronicaUsuario extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String MiPc="10.0.2.2:50197";
                URL Ruta = new URL("http://" + MiPc + "/api/TraerUsuarioXmail/" + Mail  );
                HttpURLConnection miConexion = (HttpURLConnection) Ruta.openConnection();
                Log.d("Ruta","http://" + MiPc + "/api/TraerUsuarioXmail/" + Mail+"@gmail.com");
                Log.d("AccesoApiPerfil", "Me Conecto");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("AccesoApiPerfil", "Conexion Ok");
                    Log.d("AccesoApiPerfil", Ruta.getPath());
                    InputStream cuerpoRespuesta = miConexion.getInputStream();
                    InputStreamReader lectorRespuesta = new InputStreamReader(cuerpoRespuesta, "UTF-8");
                    JsonReader JSONleido = new JsonReader(lectorRespuesta);
                    JsonParser parseadorDeJson;
                    parseadorDeJson = new JsonParser();
                    JsonObject objJson;
                    objJson = parseadorDeJson.parse(lectorRespuesta).getAsJsonObject();
                    Mail = objJson.get("Email").getAsString();
                    nombreUsuario = objJson.get("NombreUsuario").getAsString();
                    apellido = objJson.get("Apellido").getAsString();
                    nombre = objJson.get("Nombre").getAsString();
                    Log.d("DatosPerfil", Mail);
                    Log.d("DatosPerfil", nombreUsuario);
                    Log.d("DatosPerfil", nombre);
                    Log.d("DatosPerfil", apellido);



                } else {
                    Log.d("AccesoApi", "error");
                }
                Log.d("AccesoApi", "???");
                miConexion.disconnect();

            } catch (Exception Error) {
                Log.d("AccesoApi", "Hubo un error al conectarse " + Error.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            usuario1.setText(nombreUsuario);

            nombre1.setText(nombre);

            apellido1.setText(apellido);

            mail1.setText(Mail);

            Log.d("DatosNuevos","Usuario Enviado: "+usuario1.toString()+" Nombre Enviado: "+nombre1.toString()+" Apellido Enviado: " + apellido1.toString());


        }


    }
    void homePresionado(View v){
        Intent intent = new Intent(VerPerfil.this, Home.class);
        startActivity(intent);
    }

}
