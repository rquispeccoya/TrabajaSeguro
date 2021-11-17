package com.proyectofinal.trabajoseguro.viewmodels;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.proyectofinal.trabajoseguro.InterfazUsuarioActivity;
import com.proyectofinal.trabajoseguro.LoginActivity;
import com.proyectofinal.trabajoseguro.model.DataEmpresa;
import com.proyectofinal.trabajoseguro.model.Empresa;

public class InterfazRegistroViewModel extends BaseObservable {
    private Context context;
    private Empresa empresa;

    public InterfazRegistroViewModel(Context context) {
        this.context = context;
        empresa = new Empresa();
    }

    public void setEmpresaNombre(String nombre) {empresa.setNombre(nombre);}
    public void setEmpresaEncargado(String encargado) {empresa.setEncargado(encargado);}
    public void setEmpresaCorreo(String correo) {empresa.setCorreo(correo);}
    public void setEmpresaTelefono(String telefono) {empresa.setTelefono(telefono);}
    public void setEmpresaRuc(String ruc) {empresa.setRuc(ruc);}
    public void setEmpresaUsuario(String usuario) {empresa.setUsuario(usuario);}
    public void setEmpresaContrasenia(String contrasenia) {empresa.setContrasenia(contrasenia);}
    public void setEmpresaDescripcion(String descripcion) {empresa.setDescripcion(descripcion);}
    public void setEmpresaDireccion(String direccion) {empresa.setDireccion(direccion);}
    public void setEmpresaDepartamento(String departamento) {empresa.setDepartamento(departamento);}

    @Bindable
    public String getEmpresaNombre() {return empresa.getNombre();}
    @Bindable public String getEmpresaEncargado() {return empresa.getEncargado();}
    @Bindable public String getEmpresaCorreo() {return empresa.getCorreo();}
    @Bindable public String getEmpresaTelefono() {return empresa.getTelefono();}
    @Bindable public String getEmpresaRuc() {return empresa.getRuc();}
    @Bindable public String getEmpresaUsuario() {return empresa.getUsuario();}
    @Bindable public String getEmpresaContrasenia() {return empresa.getContrasenia();}
    @Bindable public String getEmpresaDescripcion() {return empresa.getDescripcion();}
    @Bindable public String getEmpresaDireccion() {return empresa.getDireccion();}
    @Bindable public String getEmpresaDepartamento() {return empresa.getDepartamento();}

    public void onClickRegistrar() {
        DataEmpresa dataEmpresa = new DataEmpresa(context.getApplicationContext());
        dataEmpresa.registrarEmpresa(empresa);
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}
