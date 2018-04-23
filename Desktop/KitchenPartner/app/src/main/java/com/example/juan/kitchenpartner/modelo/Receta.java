package com.example.juan.kitchenpartner.modelo;

import android.media.Image;

import java.util.Set;

/**
 * Created by juana on 29/03/2018.
 */

public class Receta {

    private int i_id_receta;
    private Image img;
    private String s_nombre;
    private String s_categoria;
    private String s_elaboracion;
    private Set<Ingrediente> set_ingredientes;
    private boolean b_favorita =false;

    public int getI_id_receta() {
        return i_id_receta;
    }

    public void setI_id_receta(int i_id_receta) {
        this.i_id_receta = i_id_receta;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getS_nombre() {
        return s_nombre;
    }

    public void setS_nombre(String s_nombre) {
        this.s_nombre = s_nombre;
    }

    public String getS_categoria() {
        return s_categoria;
    }

    public void setS_categoria(String s_categoria) {
        this.s_categoria = s_categoria;
    }

    public String getS_elaboracion() {
        return s_elaboracion;
    }

    public void setS_elaboracion(String s_elaboracion) {
        this.s_elaboracion = s_elaboracion;
    }

    public Set<Ingrediente> getSet_ingredientes() {
        return set_ingredientes;
    }

    public void setSet_ingredientes(Set<Ingrediente> set_ingredientes) {
        this.set_ingredientes = set_ingredientes;
    }

    public boolean isB_favorita() {
        return b_favorita;
    }

    public void setB_favorita(boolean b_favorita) {
        this.b_favorita = b_favorita;
    }
}
