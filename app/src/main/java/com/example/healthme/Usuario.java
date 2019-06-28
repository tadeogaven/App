package com.example.healthme;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int _id;
    private String _nombre;
    private String _apellido;
    private String _email;
    private String _nombreUsuario;

    public Usuario(int id, String nombre, String apellido, String email, String nombreUsuario){
        _id = id;
        _nombre = nombre;
        _apellido = apellido;
        _email = email;
        _nombreUsuario = nombreUsuario;
    }

    public int getId() {
        return _id;
    }

    public String getNombre() {
        return _nombre;
    }

    public String getApellido() {
        return _apellido;
    }

    public String getEmail() {
        return _email;
    }

    public String getNombreUsuario() {
        return _nombreUsuario;
    }
}
