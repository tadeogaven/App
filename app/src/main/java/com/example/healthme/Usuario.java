package com.example.healthme;

import java.io.Serializable;

public class Usuario{
    private int _id;
    private String _nombre;
    private String _apellido;
    private String _email;
    private String _nombreUsuario;
    private String _pass;

    public void setId(int _id) {
        this._id = _id;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setApellido(String _apellido) {
        this._apellido = _apellido;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public void setNombreUsuario(String _nombreUsuario) {
        this._nombreUsuario = _nombreUsuario;
    }
    public void setPass(String _pass) {
        this._pass = _pass;
    }

    public Usuario(int id, String nombre, String apellido, String email, String nombreUsuario, String pass){
        _id = id;
        _nombre = nombre;
        _apellido = apellido;
        _email = email;
        _nombreUsuario = nombreUsuario;
        _pass= pass;
    }
    public Usuario() {}

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
    public String getPass() {
        return _pass;
    }

}
