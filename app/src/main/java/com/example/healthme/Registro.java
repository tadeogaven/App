package com.example.healthme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
    }
    void RegistroCompleto_Presionado(View regcompleto){
        Intent intent = new Intent(Registro.this, ElegirDieta.class);
        startActivity(intent);
    }
}
