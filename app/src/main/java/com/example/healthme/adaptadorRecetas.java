package com.example.healthme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.healthme.R;
import com.example.healthme.Recetas;

import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class adaptadorRecetas extends BaseAdapter {
        public Bitmap imagen;
    private ArrayList<Recetas> ListRecetas;
    private Context Contexto;

    public adaptadorRecetas(ArrayList<Recetas> Lista, Context Contexto) {
        ListRecetas = Lista;
        this.Contexto = Contexto;
    }

    public int getCount() {
        return ListRecetas.size();
    }

    public Recetas getItem(int Position) {
        Recetas recetas;
        recetas = ListRecetas.get(Position);
        return recetas;
    }

    public long getItemId(int Position) {
        return 148024;
    }

    public View getView(int Position, View view, ViewGroup GrupoActual) {
        View VistaADevolver;
        VistaADevolver = null;

        LayoutInflater infladorDeLayout;
        infladorDeLayout = (LayoutInflater) Contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        VistaADevolver = infladorDeLayout.inflate(R.layout.receta_caracteristica, GrupoActual, false);

        Recetas recetaActual = getItem(Position);
        String Nombre = recetaActual._nombre;
        String Tiempo = recetaActual._tiempo;
        final String Imagen = recetaActual._imagen;
        String  Descripcion = recetaActual._descripción;

        TextView NombreReceta;
        NombreReceta = (TextView) VistaADevolver.findViewById(R.id.nombre);
        NombreReceta.setText(Nombre);
        TextView TiempoRec;
        TiempoRec = (TextView) VistaADevolver.findViewById(R.id.tiempo);
        TiempoRec.setText(Tiempo);
        TextView Desc;
        Desc =(TextView) VistaADevolver.findViewById(R.id.desc);
        Desc.setText(Descripcion);

        final ImageView ImagenReceta;
        ImagenReceta = (ImageView) VistaADevolver.findViewById(R.id.imagen);

        final class descargarImagen extends AsyncTask<String, Void, Bitmap> {
            protected Bitmap doInBackground(String... ruta){

                imagen = null;
                try{
                    URL miRuta;
                    miRuta = new URL(ruta[0]);
                    HttpURLConnection conexion;
                    conexion = (HttpURLConnection) miRuta.openConnection();
                    if(conexion.getResponseCode()==200){
                        InputStream cuerpoDatos = conexion.getInputStream();
                        BufferedInputStream lectorEntrada = new BufferedInputStream(cuerpoDatos);
                        imagen = BitmapFactory.decodeStream(lectorEntrada);
                        conexion.disconnect();
                    }
                } catch(Exception e){
                    Log.d("error", "Error " + e.getMessage());
                }
                return imagen;
            }
            protected void onPostExecute (Bitmap imagen){
                if (imagen!=null){
                    ImagenReceta.setImageBitmap(imagen);
                } else{
                    ImagenReceta.setImageResource(android.R.drawable.ic_dialog_alert);
                }
            }

            public void applyImage(View view) {
                try {
                    if(imagen==null)
                        imagen = this.get();
                    ((ImageView) view.findViewById(R.id.imagen)).setImageBitmap(imagen);
                } catch (Exception e) {
                    Log.d("movie","Failed to download image: "+e.getMessage());
                }
            }
        }
        descargarImagen tarea = new descargarImagen();
        tarea.execute(Imagen);

        return VistaADevolver;
    }
}
