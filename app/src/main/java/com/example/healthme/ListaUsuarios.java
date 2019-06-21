package com.example.healthme;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class ListaUsuarios extends AppCompatActivity {

    ArrayList<String> listaUsuarios;
    ListView miListaDeUsuarios;
    ArrayAdapter miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_usuarios);
        listaUsuarios = new ArrayList<>();
        miListaDeUsuarios = findViewById(R.id.miListaDeUsuarios);
        miAdaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaUsuarios);
        tareaAsincronica miTarea = new tareaAsincronica();
        miTarea.execute();

        Log.d("AccesoApi", "Termine la ejecucion");
    }

    public static String jsonToString(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public void procesarJSONleido(InputStreamReader streamLeido) {
        Log.d("LecturaJSON","eNTRO");
        try {
            JSONArray listaUsuarios = new JSONArray(jsonToString(streamLeido));

            for(int i = 0; i < listaUsuarios.length(); i++){

                JSONObject usuario = listaUsuarios.getJSONObject(i);

                Log.d("A", usuario.getString("IdUsuario"));
                Log.d("A", usuario.getString("Apellido"));
                Log.d("A", usuario.getString("Nombre"));
                Log.d("A", usuario.getString("NomUsuario"));
                Log.d("A", usuario.getString("Email"));


            }


        } catch (Exception error) {
            Log.d("LecturaJSON", "hubo un error" + error.getMessage());
        }
    }


    class tareaAsincronica extends AsyncTask<Void, Void, Void> {


        String miPC="10.0.2.2:50197";

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL miRuta = new URL("http://"+ miPC +"/api/ObtenerUsuarios");
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                Log.d("AccesoApi", "Me Conecto");
                if (miConexion.getResponseCode() == 200) {

                    Log.d("AccesoApi", "Conexion Ok");
                    InputStream cuerpoRespuesta = miConexion.getInputStream();
                    InputStreamReader lectorRespuesta = new InputStreamReader(cuerpoRespuesta, "UTF-8");
                    procesarJSONleido(lectorRespuesta);
                } else {

                    Log.d("AccesoApi", "error");
                }
                miConexion.disconnect();

            } catch (Exception Error) {

                Log.d("AccesoApi", "Hubo un error al conectarse " + Error.getMessage());

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            miListaDeUsuarios.setAdapter(miAdaptador);

        }

    }
}
