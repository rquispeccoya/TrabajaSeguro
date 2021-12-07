package com.proyectofinal.trabajoseguro.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.proyectofinal.trabajoseguro.LoginActivity;
import com.proyectofinal.trabajoseguro.model.DAO.DataEmpresa;
import com.proyectofinal.trabajoseguro.model.entity.Empresa;

import java.util.regex.Pattern;

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

        if(validate()) {
            DataEmpresa dataEmpresa = new DataEmpresa(context.getApplicationContext());
            dataEmpresa.registrarEmpresa(empresa);
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);

        }
    }

    private boolean validate(){
        boolean resultC=validarEmail(empresa.getCorreo());
        System.out.println(resultC);
        boolean resultT=validarMovilER(empresa.getTelefono());
        System.out.println(resultT);
        boolean resultR=validarRUC(empresa.getRuc());
        System.out.println(resultR);
        if(resultC && resultT && resultR){
            return true;
        }else{
            return false;
        }
    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        // boolean espacioVacio=email.equals(" ");
        System.out.println("valor de email"+email);


        boolean emailValidado=pattern.matcher(email).matches();
        if(emailValidado==false){
            Toast.makeText(context.getApplicationContext(), "Por favor ingresar un correo electrónico válido", Toast.LENGTH_LONG).show();
        }

        return  emailValidado;
    }
    private boolean validarMovilER(String movil) {
        Pattern pattern = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{3}");
        //  boolean espacioVacio;
        /*
        if (movil.equals(" ")) {
            //    Toast.makeText(context.getApplicationContext(), "Ha dejado el campo vacio", Toast.LENGTH_SHORT).show();
            System.out.println("espacio vacio del movil");
        }
        */

        boolean movilValidado=pattern.matcher(movil).matches();
        if(movilValidado==false){
            Toast.makeText(context.getApplicationContext(), "Por favor ingresar un número válido", Toast.LENGTH_LONG).show();
        }
        return movilValidado;
    }
    private boolean validarRUC(String ruc){
        Pattern pattern = Pattern.compile("[0-9]{11}");
        //   if(ruc.equals(" ")){
        //     Toast.makeText(context.getApplicationContext(), "Ha dejado el campo vacio", Toast.LENGTH_SHORT).show();
        //      System.out.println("espacio vacio del ruc");
        //  }

        boolean rucValidado=pattern.matcher(ruc).matches();
        if(rucValidado==false){
            Toast.makeText(context.getApplicationContext(), "Por favor ingresar ruc válido", Toast.LENGTH_LONG).show();
        }
        return rucValidado;
    }

}
