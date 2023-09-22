/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio5;

/**
 *
 * @author galin
 */
public class Surtidor {

    private int capacidad;

    public Surtidor(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized int getCapacidad() {
        return capacidad;
    }

    public synchronized boolean modificarCapacidad(int litros) {
        boolean tieneDispinible = this.capacidad > litros;
        if (tieneDispinible) {
            this.capacidad = this.capacidad - litros;
            System.out.println("se resto litros de surtidor");
        } else {
            System.out.println("El surtidor no tiene combustible");
        }
        return tieneDispinible;
    }

}
