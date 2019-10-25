package com.example.healthme;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CrearReceta extends AppCompatActivity {


    EditText nombre1,descripcion1,tiempo1;
    Context ctx=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearreceta);
        descripcion1=findViewById(R.id.descripcion);
        nombre1=findViewById(R.id.nombrereceta);
        tiempo1=findViewById(R.id.duracion);

    }
    public void InsertarReceta(View v) {

        String nombre, descripcion,tiempo;

        nombre=nombre1.getText().toString();
        descripcion=descripcion1.getText().toString();
        tiempo=tiempo1.getText().toString();
        BackGround b = new BackGround();
        b.execute(nombre,descripcion,tiempo);

    }
class BackGround extends AsyncTask<String,String,String>{

        @Override

    protected String doInBackground(String...params){

            String nombre1 = params[0];
            String descripcion1 = params[1];
            String tiempo1 = params[2];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://10.0.3.2/MYCODE/app/register.php");
                String urlParams = "name="+nombre1+"&descripcion="+descripcion1+"&tiempo="+tiempo1;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

    @Override
    protected void onPostExecute(String s) {
        if(s.equals("")){
            s="Data saved successfully.";
        }
        Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
    }


        }



}


