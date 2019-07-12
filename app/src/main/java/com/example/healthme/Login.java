package com.example.healthme;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.Vector;


public class Login extends AppCompatActivity {
    EditText EmailUsuario, passwordUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ObtenerReferencias();
        SetearListeners();
    }

    private void ObtenerReferencias() {
        EmailUsuario = findViewById(R.id.email);
        passwordUsuario = findViewById(R.id.password);
    }

    private void SetearListeners() {
    }

    void IniciarSesionCompleto_Presionado(View info) {
        if (DatosValidos()) {
            tareaAsincronica miTarea = new tareaAsincronica();
            miTarea.execute();
        }
    }

    public Boolean DatosValidos() {
        Boolean blnReturnFValue = true;
        String strEMail, strPassword;
        strEMail = EmailUsuario.getText().toString();
        strPassword = passwordUsuario.getText().toString();
        if (strEMail.length() == 0) {
            EmailUsuario.setError("El campo no puede estar vacio");
            blnReturnFValue = false;
        }
        if (strPassword.length() == 0) {
            passwordUsuario.setError("El campo no puede estar vacio");
            blnReturnFValue = false;
        }
        return blnReturnFValue;
    }

    class tareaAsincronica extends AsyncTask<Void, Void, Void> {
        String Email = EmailUsuario.getText().toString();
        String Pass = passwordUsuario.getText().toString();

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String miPC = "10.0.2.2:50197";
                URL miRuta = new URL("http://" + miPC + "/api/Login/" + Email + "/" + Pass);
                HttpURLConnection miConexion = (HttpURLConnection) miRuta.openConnection();
                Log.d("AccesoApi", "Me Conecto");
                if (miConexion.getResponseCode() == 200) {
                    Log.d("AccesoApi", "Conexion Ok");
                    InputStream cuerpoRespuesta = miConexion.getInputStream();
                    InputStreamReader lectorRespuesta = new InputStreamReader(cuerpoRespuesta, "UTF-8");
                    procesarJSONleido(lectorRespuesta);
                } else {
                    Log.d("AccesoApi", "error");
                }
                Log.d("AccesoApi", "???");
                miConexion.disconnect();

            } catch (Exception Error) {
                Log.d("AccesoApi", "Hubo un error al conectarse " + Error.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public void procesarJSONleido(InputStreamReader streamLeido) {
        JsonReader JSONleido = new JsonReader(streamLeido);
        try {
            JSONleido.beginObject();
            while (JSONleido.hasNext()) {
                String nombreElementoActual = JSONleido.nextName();
                if (nombreElementoActual.equals("Email")) {
                    String mail = JSONleido.nextString();
                    if (mail.length() == 0) {
                        Log.d("test", "muy mal");
                    } else {
                        Log.d("test", "muy bien");
                    }
                } else {
                    JSONleido.skipValue();
                }
            }
        } catch (Exception error) {
            Log.d("LecturaJSON", "hubo un error" + error.getMessage());
        }
    }
}





