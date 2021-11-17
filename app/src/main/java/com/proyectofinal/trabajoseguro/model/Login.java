package com.proyectofinal.trabajoseguro.model;

public class Login {
    private String usuario;
    private String contrasenia;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String toString() {
        return "User: " + this.usuario + "\nClave: " + this.contrasenia;
    }
}
