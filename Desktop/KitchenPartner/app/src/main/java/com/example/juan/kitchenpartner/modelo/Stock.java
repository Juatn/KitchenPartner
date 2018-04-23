package com.example.juan.kitchenpartner.modelo;

/**
 * Created by juana on 29/03/2018.
 */

public class Stock {
    private Ingrediente cl_ingrediente;
    private float f_cantidad;

    public Stock(Ingrediente cl_ingrediente, float f_cantidad) {
        this.cl_ingrediente = cl_ingrediente;
        this.f_cantidad = f_cantidad;
    }

    public Ingrediente getCl_ingrediente() {
        return cl_ingrediente;
    }

    public void setCl_ingrediente(Ingrediente cl_ingrediente) {
        this.cl_ingrediente = cl_ingrediente;
    }

    public float getF_cantidad() {
        return f_cantidad;
    }

    public void setF_cantidad(float f_cantidad) {
        this.f_cantidad = f_cantidad;
    }
}
