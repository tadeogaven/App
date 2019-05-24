package com.example.healthme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HealthMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_me);
    }
        void Iniciar_Presionado(View inc){
            Intent intent = new Intent(HealthMe.this, RegistroLogin.class);
            startActivity(intent);
        }
    void Info_Presionado(View info){
        Intent intent = new Intent(HealthMe.this, Info.class);
        startActivity(intent);
    }
}
