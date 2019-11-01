package com.example.healthme;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Recetas  {
    public int _id;
    public String _nombre;
    public String _descripción;
    public String _calorías;
    public String _tiempo;
    public String _imagen;
    public String _IdDificiltad;
    public String _IdUsuario;

    public void setid(int _id) {
        this._id = _id;
    }

    public void setnombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setdescripción(String _descripción) {
        this._descripción = _descripción;
    }

    public void setcalorías(String _calorías) {
        this._calorías = _calorías;
    }

    public void settiempo(String _tiempo) {
        this._tiempo = _tiempo;
    }

    public void setimagen(String _imagen) {
        this._imagen = _imagen;
    }

    public void setIdDificiltad(String _IdDificiltad) {
        this._IdDificiltad = _IdDificiltad;
    }

    public void setIdUsuario(String _IdUsuario) {
        this._IdUsuario = _IdUsuario;
    }

    public Recetas(int id, String nombre, String descripción, String  calorías, String tiempo, String imagen, String idDificultad, String idUsuario){
        _id = id;
        _nombre = nombre;
        _descripción=descripción;
        _calorías=calorías;
        _tiempo=tiempo;
        _imagen=imagen;
        _IdDificiltad=idDificultad;
        _IdUsuario=idUsuario;
    }

    public Recetas() {}

    public int getId() {
        return _id;
    }

    public String getNombre() {
        return _nombre;
    }

    public String getDescripción() {
        return _descripción;
    }

    public String getCalorías() {
        return _calorías;
    }

    public String getTiempo() {return _tiempo;}

    public String getImagen() {
        return _imagen;
    }

    public String getIdDificultad() {
        return _IdDificiltad;
    }

    public String getIdUsuario() {
        return _IdUsuario;
    }

}

