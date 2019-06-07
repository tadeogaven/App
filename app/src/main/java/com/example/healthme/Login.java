package com.example.healthme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    void IniciarSesionCompleto_Presionado(View info){
        Intent intent = new Intent(Login.this, ListaUsuarios.class);
        startActivity(intent);
    }
}
