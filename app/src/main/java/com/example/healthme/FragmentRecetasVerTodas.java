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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FragmentRecetasVerTodas extends Fragment implements View.OnClickListener {

    ListView MiListaDeRecetas;
    ArrayList<Recetas> listRecetas = new ArrayList<Recetas>();
    adaptadorRecetas adaptadorRecetas;



    public View onCreateView(@NonNull LayoutInflater inflador, @Nullable ViewGroup grupo, @Nullable Bundle pack) {


        View VistaADevolver;
        VistaADevolver = inflador.inflate(R.layout.lv_recetas, grupo, false);

        adaptadorRecetas= new adaptadorRecetas(listRecetas,VistaADevolver.getContext());

        MiListaDeRecetas = VistaADevolver.findViewById(R.id.ListViewRecetas);

        MiListaDeRecetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Recetas recetas = new Recetas();
                    recetas = listRecetas.get(position);
                ((Home)getActivity()).ProcesarDatosRecibidos(recetas);
                }
        });

        tareaAsincronicaRecetas buscarRecetas = new tareaAsincronicaRecetas();
        buscarRecetas.execute();


        return VistaADevolver;
    }

    @Override
    public void onClick(View v) {

    }

    public class tareaAsincronicaRecetas extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String MiPc="10.0.2.2:50197";
                Log.d("Conexión","http://" + MiPc + "/api/ObtenerRecetas");
                URL Ruta = new URL("http://" + MiPc + "/api/ObtenerRecetas");
                HttpURLConnection miConexion = (HttpURLConnection) Ruta.openConnection();
                if (miConexion.getResponseCode() == 200) {
                    Log.d("Conexión","se conecto");
                    InputStream stream = miConexion.getInputStream();
                    InputStreamReader reader = new InputStreamReader(stream, "UTF-8");
                    JsonParser parser = new JsonParser();
                    JsonArray json = parser.parse(reader).getAsJsonArray();
                    Log.d("eskere", "" + json.size());
                    for (int i = 0; i < json.size(); i++) {
                        JsonObject jsonRec = json.get(i).getAsJsonObject();
                        Recetas recetas = new Recetas();
                        recetas._nombre = jsonRec.get("Nombre").getAsString();
                        recetas._tiempo = jsonRec.get("Tiempo").getAsString();
                        recetas._imagen = jsonRec.get("Imagen").getAsString();
                        recetas._calorías = jsonRec.get("Calorias").getAsString();
                        //recetas._imagen = jsonRec.get("Imagen").getAsString();
                       // recetas._IdDificiltad = jsonRec.get("IdDificultad").getAsString();
                        recetas._IdUsuario = jsonRec.get("IdUsuario").getAsString();
                        recetas._descripción = jsonRec.get("Descripcion").getAsString();
                        // recetas._id = jsonRec.get("Id").getAsInt();
                        listRecetas.add(recetas);
                        Log.d("Recetas", recetas._nombre);
                        Log.d("Recetas", recetas._tiempo);
                        Log.d("Recetas", recetas._IdUsuario);


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
            Log.d("eskere", "" + listRecetas.size());
            MiListaDeRecetas.setAdapter(adaptadorRecetas);
        }

    }
}
