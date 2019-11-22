package com.example.healthme;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.BufferUnderflowException;


abstract public class FragmentReceta_Detalle extends Fragment implements View.OnClickListener {
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
    adaptadorRecetas adapter;
    View vistaADevolver;
    

    public void setNombre(Recetas laReceta) {
        receta = laReceta;
    }

    public View onCreateView (LayoutInflater infladorDeLayout, ViewGroup grupo, Bundle Datos){


        vistaADevolver = infladorDeLayout.inflate(R.layout.lv_recetas_detalle, grupo, false);;
        home = (Home) getActivity();
        nombreReceta = home.LeeEstaReceta();
        imagen = vistaADevolver.findViewById(R.id.imagen);
        nombre = vistaADevolver.findViewById(R.id.nombre);
        tiempo = vistaADevolver.findViewById(R.id.tiempo);
        dificultad = vistaADevolver.findViewById(R.id.dificultad);
        calorias = vistaADevolver.findViewById(R.id.calorias);
        //usuario = vistaADevolver.findViewById(R.id.usuario);
        descripcion = vistaADevolver.findViewById(R.id.descripcion);



        descripcion.setText(receta._descripción);
        nombre.setText(receta._nombre);
        tiempo.setText(receta._tiempo);
        dificultad.setText(receta._IdDificiltad);
        calorias.setText(receta._calorías);
        //usuario.setText(receta._IdUsuario);



        return vistaADevolver;


    }

    void setBotonVerUsuario(View v){
        receta.getIdUsuario();
    }


}


