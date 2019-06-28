package com.example.healthme;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.HashMap;

public class ListaUsuarios extends AppCompatActivity {
    ListView miListaDeUsuarios;
    ArrayAdapter miAdaptador;
    HashMap<Integer, Usuario> listaUsuarios;

    AdapterView.OnItemClickListener escuhadorPorListView = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView parent, View view, int usuarioSeleccionado, long id) {
            Log.d("test", "SADFSDOGISBHIGUOSDBNGIU");
            Bundle paquete = new Bundle();

            paquete.putSerializable("usuario", listaUsuarios.get(usuarioSeleccionado));

            Intent actividadDetalleUsuario = new Intent(ListaUsuarios.this, DetalleUsuario.class);
            actividadDetalleUsuario.putExtras(paquete);

            startActivity(actividadDetalleUsuario);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_usuarios);
        listaUsuarios = new HashMap<>();
        miListaDeUsuarios = findViewById(R.id.miListaDeUsuarios);
        miListaDeUsuarios.setOnItemClickListener(escuhadorPorListView);
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
            JSONArray listaUsuariosJSON = new JSONArray(jsonToString(streamLeido));

            for(int i = 0; i < listaUsuariosJSON.length(); i++){

                JSONObject jsonUsuario = listaUsuariosJSON.getJSONObject(i);
                Usuario usuario = new Usuario(jsonUsuario.getInt("IdUsuario"),
                        jsonUsuario.getString("Nombre"),
                        jsonUsuario.getString("Apellido"),
                        jsonUsuario.getString("Email"),
                        jsonUsuario.getString("NomUsuario"));

                listaUsuarios.put(i, usuario);
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

            ArrayList<String> nombresDeUsuario = new ArrayList<>();
            for(Usuario u : listaUsuarios.values()){
                nombresDeUsuario.add(u.getNombreUsuario());
            }
            miAdaptador = new ArrayAdapter<>(ListaUsuarios.this, android.R.layout.simple_list_item_1, nombresDeUsuario);
            miListaDeUsuarios.setAdapter(miAdaptador);
        }
    }
}
