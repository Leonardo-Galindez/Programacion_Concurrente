/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_0.Ejercicio3;

/**
 *
 * @author galin
 */
public class Desarrollador extends Empleado {

    private String titulo;
    private String rol;
    private static final int sueldo = 10000;

    public Desarrollador(String titulo, String rol, String legajo, int antiguedad, String nombre, int dni, String direccion, String fechaNacimiento, String sexo) {
        super(legajo, antiguedad, nombre, dni, direccion, fechaNacimiento, sexo);
        this.titulo = titulo;
        this.rol = rol;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Desarrollador{" + super.toString() + "titulo=" + titulo + ", rol=" + rol + '}';
    }

}
