package com.proyectofinal.trabajoseguro.viewmodels;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.proyectofinal.trabajoseguro.BR;
import com.proyectofinal.trabajoseguro.model.DAO.DataEmpresa;
import com.proyectofinal.trabajoseguro.model.entity.Empresa;

public class HomeViewModel extends BaseObservable {
    private String id;
    private Context context;
    private Empresa empresa;
    public HomeViewModel(String id,Context context){
        this.id=id;
        this.context = context;
        DataEmpresa dataEmpresa = new DataEmpresa(this.context.getApplicationContext());
        empresa=dataEmpresa.buscarEmpresa(this.id);
        setNombreEmpresa(empresa.getNombre());
        setEncargadoEmpresa(empresa.getEncargado());
        setCorreoEmpresa(empresa.getCorreo());
        setTelefonoEmpresa(empresa.getTelefono());
        setUsuarioEmpresa(empresa.getUsuario());
        setDescripcionEmpresa(empresa.getDescripcion());
        setDepartamentoEmpresa(empresa.getDepartamento());
    }

    @Bindable
    public String getNombreEmpresa() {
        return empresa.getNombre();
    }
    public void setNombreEmpresa(String nombre){
        empresa.setNombre(nombre);
        notifyPropertyChanged(BR.nombreEmpresa);
    }

    @Bindable
    public String getDescripcionEmpresa() {
        return empresa.getDescripcion();
    }
    public void setDescripcionEmpresa(String descripcion){
        empresa.setDescripcion(descripcion);
        notifyPropertyChanged(BR.descripcionEmpresa);
    }

    @Bindable
    public String getEncargadoEmpresa() {
        return empresa.getEncargado();
    }
    public void setEncargadoEmpresa(String encargado){
        empresa.setEncargado(encargado);
        notifyPropertyChanged(BR.encargadoEmpresa);
    }

    @Bindable
    public String getCorreoEmpresa() {
        return empresa.getCorreo();
    }
    public void setCorreoEmpresa(String correo){
        empresa.setCorreo(correo);
        notifyPropertyChanged(BR.correoEmpresa);
    }

    @Bindable
    public String getTelefonoEmpresa() {
        return empresa.getTelefono();
    }
    public void setTelefonoEmpresa(String telefono){
        empresa.setTelefono(telefono);
        notifyPropertyChanged(BR.telefonoEmpresa);
    }

    @Bindable
    public String getUsuarioEmpresa() {
        return empresa.getUsuario();
    }
    public void setUsuarioEmpresa(String usuario){
        empresa.setUsuario(usuario);
        notifyPropertyChanged(BR.usuarioEmpresa);
    }

    @Bindable
    public String getDepartamentoEmpresa() {
        return empresa.getDepartamento();
    }
    public void setDepartamentoEmpresa(String departamento){
        empresa.setTelefono(departamento);
        notifyPropertyChanged(BR.departamentoEmpresa);
    }

}
