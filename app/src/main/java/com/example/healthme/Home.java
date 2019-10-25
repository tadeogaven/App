package com.example.healthme;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {

    EditText buscar;
    Button boton;
    Button botoncrear;
    String nombre;
    FragmentManager adminFragment;
    FragmentTransaction transacFragment;
    Fragment fragmentRecetasPorNombre;
    Fragment fragmentRecetas;
    Fragment fragmentReceta_Detalle;
    adaptadorRecetas adaptadorRecetas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);





        //buscar=findViewById(R.id.Buscar);

        adminFragment = getFragmentManager();
        fragmentRecetasPorNombre = new FragmentRecetas();
        fragmentRecetas = new FragmentRecetasVerTodas();
        fragmentReceta_Detalle = new FragmentReceta_Detalle();
        VerTodas();
    }

    public void Buscar (View vista){
        nombre = buscar.getText().toString();
        transacFragment = adminFragment.beginTransaction();
        transacFragment.replace(R.id.FragmentHome, fragmentRecetasPorNombre);
        transacFragment.commit();
    }

    public String LeeEstaReceta (){ return nombre; }

    public void VerTodas(){
        Log.d("eskere", "VerTodas");
        transacFragment = adminFragment.beginTransaction();
        transacFragment.replace(R.id.FragmentHome, fragmentRecetas);
        transacFragment.commit();
    }

    public void ProcesarDatosRecibidos (Recetas laReceta){
        //nombre = nombreRec;
        transacFragment = adminFragment.beginTransaction();
        transacFragment.replace(R.id.FragmentHome, fragmentReceta_Detalle);
        transacFragment.commit();
        ((FragmentReceta_Detalle)fragmentReceta_Detalle).setNombre(laReceta);
    }

    private void crearFragnent(int id, Fragment fragment, String tag) {
        adminFragment = getFragmentManager();
        transacFragment = adminFragment.beginTransaction();
        transacFragment.replace(id,fragment,tag);
        transacFragment.commit();
    }


}
