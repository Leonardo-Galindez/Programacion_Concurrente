/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_0.Ejercicio3;

/**
 *
 * @author galin
 */
public class Administrativo extends Empleado {

    private String categoria;
    private int diasSinFaltar;

    public Administrativo(String categoria, String legajo, int antiguedad, String nombre, int dni, String direccion, String fechaNacimiento, String sexo) {
        super(legajo, antiguedad, nombre, dni, direccion, fechaNacimiento, sexo);
        this.categoria = categoria;
        this.diasSinFaltar = 0;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getDiasSinFaltar() {
        return diasSinFaltar;
    }

    public void setDiasSinFaltar(int diasSinFaltar) {
        this.diasSinFaltar = diasSinFaltar;
    }

    @Override
    public String toString() {
        return "Administrativo{" + super.toString() + "categoria=" + categoria + ", diasSinFaltar=" + diasSinFaltar + '}';
    }

}
