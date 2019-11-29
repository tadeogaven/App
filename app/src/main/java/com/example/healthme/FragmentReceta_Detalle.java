package com.example.healthme;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.BufferUnderflowException;


 public class FragmentReceta_Detalle extends Fragment {
     Recetas receta = new Recetas();
     ImageView imagen;
     TextView nombre;
     TextView tiempo;
     TextView dificultad;
     TextView usuario;
     TextView calorias;
     TextView descripcion;
     String nombreReceta;
     Button botonVerUsuario;
     Home home;
     String idUs;
     adaptadorRecetas adapter;
     View vistaADevolver;

     private String Mail;
     private String nombreUsuario;
     private String apellido;
     private String nombreuS;
     private TextView nombre1;
     private TextView usuario1;
     private TextView apellido1;
     private TextView mail1;


     public void setNombre(Recetas laReceta) {
         receta = laReceta;
     }

     public View onCreateView(LayoutInflater infladorDeLayout, ViewGroup grupo, Bundle Datos) {


         vistaADevolver = infladorDeLayout.inflate(R.layout.lv_recetas_detalle, grupo, false);
         ;
         home = (Home) getActivity();
         nombreReceta = home.LeeEstaReceta();
         imagen = vistaADevolver.findViewById(R.id.imagen);
         nombre = vistaADevolver.findViewById(R.id.nombre);
         tiempo = vistaADevolver.findViewById(R.id.tiempo);
        // dificultad = vistaADevolver.findViewById(R.id.dificultad);
         calorias = vistaADevolver.findViewById(R.id.calorias);
         //usuario = vistaADevolver.findViewById(R.id.usuario);
         descripcion = vistaADevolver.findViewById(R.id.descripcion);


         descripcion.setText(receta._descripción);
         nombre.setText(receta._nombre);
         tiempo.setText(receta._tiempo);
//         dificultad.setText(receta._IdDificiltad);
         calorias.setText(receta._calorías);
         //usuario.setText(receta._IdUsuario);

         FragmentReceta_Detalle.tareaAsincronicaUsuarioReceta buscarUsuariosPorMail = new tareaAsincronicaUsuarioReceta();
         buscarUsuariosPorMail.execute();

         return vistaADevolver;


     }

     public class tareaAsincronicaUsuarioReceta extends AsyncTask<Void, Void, Void> {


         @Override
         protected Void doInBackground(Void... voids) {
             try {
                 String MiPc = "10.0.2.2:50197";
                 URL Ruta = new URL("http://" + MiPc + "/api/TraerUsuarioXidYreceta/" + receta._IdUsuario.toString());
                 HttpURLConnection miConexion = (HttpURLConnection) Ruta.openConnection();
                 Log.d("Ruta", "http://" + MiPc + "/api/TraerUsuarioXidYreceta/" + receta._IdUsuario.toString());
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
                     idUs = objJson.get("Id").getAsString();
                     nombreUsuario = objJson.get("NombreUsuario").getAsString();
                     apellido = objJson.get("Apellido").getAsString();
                     nombreuS = objJson.get("Nombre").getAsString();
                     Mail = objJson.get("Email").getAsString();
                     Log.d("DatosPerfilReceta", Mail);
                     Log.d("DatosPerfilReceta", nombreUsuario);
                     Log.d("DatosPerfilReceta", nombreuS);
                     Log.d("DatosPerfilReceta", apellido);


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

            usuario1=vistaADevolver.findViewById(R.id.NombreUsuarioRec);
             usuario1.setText(nombreUsuario);

             nombre1=vistaADevolver.findViewById(R.id.nombreUs);
             nombre1.setText(nombreuS);

             apellido1=vistaADevolver.findViewById(R.id.apellidoUs);
             apellido1.setText(apellido);

             mail1=vistaADevolver.findViewById(R.id.Mail);
             mail1.setText(Mail);


             Log.d("DatosPerfilReceta", "Usuario Enviado: " + usuario1.toString() + " Nombre Enviado: " + nombre1.toString() + " Apellido Enviado: " + apellido1.toString());


         }


     }
 }


