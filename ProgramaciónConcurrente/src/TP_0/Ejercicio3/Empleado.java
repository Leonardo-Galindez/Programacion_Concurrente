/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_0.Ejercicio3;

/**
 *
 * @author galin
 */
public class Empleado extends Persona {

    private String legajo;
    private int antiguedad;

    public Empleado(String legajo, int antiguedad, String nombre, int dni, String direccion, String fechaNacimiento, String sexo) {
        super(nombre, dni, direccion, fechaNacimiento, sexo);
        this.legajo = legajo;
        this.antiguedad = antiguedad;
    }

    public String getLegajo() {
        return legajo;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public boolean verificarAntiguedad() {
        return this.antiguedad > 10;
    }

    //override sirve para sobrescribir el mismo metodo utilizado en las sub clases
    @Override
    public String toString() {
        return "Empleado{" + super.toString() + "legajo=" + legajo + ", antiguedad=" + antiguedad + '}';
    }

}
