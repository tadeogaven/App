package com.example.healthme;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FragmentRecetas extends Fragment {

    ListView MiListaDeRecetas;
    String Nombre;
    ArrayList<Recetas> listRecetas = new ArrayList<Recetas>();

    public View onCreateView(@NonNull LayoutInflater inflador, @Nullable ViewGroup grupo, @Nullable Bundle pack) {
        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.lv_recetas, grupo, false);




        MiListaDeRecetas = VistaADevolver.findViewById(R.id.ListViewRecetas);

        MiListaDeRecetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recetas recetas = listRecetas.get(position);

            }
        });

        tareaAsincronicaRecetas buscarRecetasPorNombre = new tareaAsincronicaRecetas(Nombre);
        buscarRecetasPorNombre.execute();


        return VistaADevolver;
    }

    public class tareaAsincronicaRecetas extends AsyncTask<Void, Void, Void> {



        public tareaAsincronicaRecetas(String name) {


            Nombre = name;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Log.d("eskere", Nombre);
                String MiPc="10.0.2.2:50197";
                URL Ruta = new URL("http://" + MiPc + "/api/TraerRecetas/" + Nombre);
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
                        Recetas recetas = new Recetas();
                        recetas._nombre = jsonRec.get("Nombre").getAsString();
                        recetas._tiempo = jsonRec.get("Tiempo").getAsString();
                        recetas._imagen = jsonRec.get("Imagen").getAsString();
                        recetas._calorías = jsonRec.get("Calorías").getAsString();
                        recetas._imagen = jsonRec.get("Imagen").getAsString();
                        recetas._IdDificiltad = jsonRec.get("IdDificultad").getAsString();
                        recetas._IdUsuario = jsonRec.get("IdUsuario").getAsString();
                        recetas._id = jsonRec.get("Id").getAsInt();
                        listRecetas.add(recetas);
                        Log.d("Recetas", recetas._nombre);
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
        }

    }
}
