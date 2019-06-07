package com.example.healthme;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
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
    public void procesarJSONleido(InputStreamReader streamLeido) {
        JsonReader JSONleido = new JsonReader(streamLeido);
        try {
            JSONleido.beginObject();
            while (JSONleido.hasNext()) {
                String nombreElementoActual = JSONleido.nextName();
                if (nombreElementoActual.equals("")) {
                    int cantidadCategoria = JSONleido.nextInt();
                    //Log.d("LecturaJSON", "La cant de categorias es " + cantidadCategoria);

                } else {
                    JSONleido.beginArray();
                    while (JSONleido.hasNext()) {
                        JSONleido.beginObject();
                        while (JSONleido.hasNext()) {
                            nombreElementoActual = JSONleido.nextName();
                            if (nombreElementoActual.equals("Nombre")) {
                                String valorElementoActual = JSONleido.nextString();
                                Log.d("LecturaJSON", "valor" + valorElementoActual);
                                listaUsuarios.add(valorElementoActual);
                            } else {
                                JSONleido.skipValue();
                            }
                        }
                        JSONleido.endObject();
                    }
                    JSONleido.endArray();
                }
            }
        } catch (Exception error) {
            Log.d("LecturaJSON", "hubo un error" + error.getMessage());
        }
    }


    class tareaAsincronica extends AsyncTask<Void, Void, Void> {

        String miPC="10.0.2.2";

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL miRuta = new URL("http://"+ miPC +":50197/api/ObtenerUsuarios");
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
