package com.example.healthme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        Button btnRegistro;
        btnRegistro=findViewById(R.id.btnRegistro);
       Intent intent = new Intent(RegistroLogin.this, Registro.class);
        startActivity(intent);
    }
    void IniciarSesion_Presionado (View InSes){
        Intent intent = new Intent(RegistroLogin.this, Login.class);
        startActivity(intent);
    }
}
