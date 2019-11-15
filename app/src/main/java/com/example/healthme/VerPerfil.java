package com.example.healthme;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VerPerfil extends AppCompatActivity {
    String Mail;
    String nombreUsuario;
    String apellido;
    String nombre;
    TextView nombre1;
    TextView usuario1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("gdfs", "entra");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_perfil);

        Log.d("gdfs",this.getIntent().getExtras().toString() );
        Bundle bundle = this.getIntent().getExtras();
        Log.d("gdfs", bundle.toString());
        Log.d("gdfs", bundle.toString());
        Mail = bundle.getString("Mail");
        Log.d("El mail es", Mail);
        Log.d("El NombreUsuario es", nombreUsuario);
        tareaAsincronicaUsuario buscarUsuariosPorMail = new tareaAsincronicaUsuario();
        buscarUsuariosPorMail.execute();
    }

    public class tareaAsincronicaUsuario extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Log.d("Mail", Mail);
                String MiPc="10.0.2.2:50197";
                URL Ruta = new URL("http://" + MiPc + "/api/TraerUsuarioXmail/" + Mail);
                HttpURLConnection miConexion = (HttpURLConnection) Ruta.openConnection();
                if (miConexion.getResponseCode() == 200) {
                    InputStream stream = miConexion.getInputStream();
                    InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
                    JsonParser parser = new JsonParser();
                    JsonObject json = parser.parse(reader).getAsJsonObject();

                    JsonArray jsonRecetas = json.getAsJsonArray("Id");
                    Log.d("eskere", "" + jsonRecetas.size());
                    for (int i = 0; i < jsonRecetas.size(); i++) {
                        JsonObject jsonRec = jsonRecetas.get(i).getAsJsonObject();
                        Usuario user = new Usuario();
                        apellido = user.getApellido();
                        apellido = jsonRec.get("Apellido").getAsString();
                        nombreUsuario = user.getNombreUsuario();
                        nombreUsuario= jsonRec.get("NombreUsuario").getAsString();
                        nombre= user.getNombreUsuario();
                        nombre= jsonRec.get("Nombre").getAsString();


                        Log.d("Usuario", user.getEmail());
                    }
                }
                miConexion.disconnect();
                Log.d("Estado", "se desconecto");

            } catch (Exception error) {
                Log.d("error", "es" + error.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            usuario1= findViewById(R.id.NombreUsuario);
            usuario1.setText(nombreUsuario);
        }

    }
}
