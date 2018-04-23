package com.example.juan.kitchenpartner.modelo;

/**
 * Created by juana on 19/04/2018.
 */

public class Comentario {

    private Usuario cl_usuario;
    private String s_mensaje;
    private float f_nota_receta;


    public Usuario getCl_usuario() {
        return cl_usuario;
    }

    public void setCl_usuario(Usuario cl_usuario) {
        this.cl_usuario = cl_usuario;
    }

    public String getS_mensaje() {
        return s_mensaje;
    }

    public void setS_mensaje(String s_mensaje) {
        this.s_mensaje = s_mensaje;
    }

    public float getF_nota_receta() {
        return f_nota_receta;
    }

    public void setF_nota_receta(float f_nota_receta) {
        this.f_nota_receta = f_nota_receta;
    }


}
