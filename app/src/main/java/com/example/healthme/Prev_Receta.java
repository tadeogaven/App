package com.example.healthme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Prev_Receta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev__receta);
    }
void crearReceta(View view) {
    Intent intent = new Intent(Prev_Receta.this, CrearReceta.class);
    startActivity(intent);
}
    void Home(View view) {
        Intent intent = new Intent(Prev_Receta.this, Home.class);
        startActivity(intent);
    }
}
