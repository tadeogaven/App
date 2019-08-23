package com.example.healthme;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Home extends AppCompatActivity {

    EditText buscar;
    Button boton;
    String nombre;
    FragmentManager adminFragment;
    FragmentTransaction transacFragment;
    Fragment fragmentRecetasPorNombre;
    Fragment fragmentRecetas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        buscar=findViewById(R.id.Buscar);

        adminFragment = getFragmentManager();
        fragmentRecetasPorNombre = new FragmentRecetas();
        fragmentRecetas = new FragmentRecetasVerTodas();
    }

    public void Buscar (View vista){
        nombre = buscar.getText().toString();
        transacFragment = adminFragment.beginTransaction();
        transacFragment.replace(R.id.FragmentHome, fragmentRecetasPorNombre);
        transacFragment.commit();
    }

    public void VerTodas(View vista){
        transacFragment = adminFragment.beginTransaction();
        transacFragment.replace(R.id.FragmentHome, fragmentRecetas);
        transacFragment.commit();
    }
}
