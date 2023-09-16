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

    public synchronized void modificarCapacidad(int litros) {
        if (this.capacidad > litros) {
            this.capacidad = this.capacidad - litros;
        } else {
            System.out.println("El surtidor no tiene combustible");
        }

    }

}
