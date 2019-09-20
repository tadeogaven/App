package com.example.healthme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

public class RegistroLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_login);
    }
    void Registro_Presionado(View regis){
       Intent intent = new Intent(RegistroLogin.this, Registro.class);
        startActivity(intent);
    }
    void IniciarSesion_Presionado (View InSes){
        Intent intent = new Intent(RegistroLogin.this, Login.class);
        startActivity(intent);

    }
    void Anonimo_Presionado (View Anonimo){
        Intent intent = new Intent(RegistroLogin.this, Home.class);
        startActivity(intent);



    }

}
