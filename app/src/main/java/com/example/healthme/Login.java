package com.example.healthme;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class Login extends AppCompatActivity {
    EditText EmailUsuario = findViewById(R.id.email);
    EditText passwordUsuario = findViewById(R.id.password);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ROTO","ANDA");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

    }

   void IniciarSesionCompleto_Presionado(View info) {

        Intent intent = new Intent(Login.this, ListaUsuarios.class);
        startActivity(intent);

    }


        public boolean ValidarEmail() {
            String Email = EmailUsuario.toString();
            String Pass = passwordUsuario.toString();
            if (Email == null) {
                EmailUsuario.setError("El campo no puede estar vacio");
                return false;
            } else {
                EmailUsuario.setError(null);
                return true;
            }


        }
    }





