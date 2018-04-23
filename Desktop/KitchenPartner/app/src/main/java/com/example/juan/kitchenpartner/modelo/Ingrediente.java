package com.example.juan.kitchenpartner.modelo;

/**
 * Created by juana on 29/03/2018.
 */

public class Ingrediente {

    private int s_id_ingrediente;
    private String s_nombre;
    private String s_categoria;
    private float f_precio;

    public int getIdIngrediente() {
        return s_id_ingrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        this.s_id_ingrediente = idIngrediente;
    }

    public String getNombre() {
        return s_nombre;
    }

    public void setNombre(String nombre) { this.s_nombre = nombre;}

    public String getCategoria() {
        return s_categoria;
    }

    public void setCategoria(String categoria) {
        this.s_categoria = categoria;
    }

    public float getPrecio() {
        return f_precio;
    }

    public void setPrecio(float precio) {
        this.f_precio = precio;
    }
}
