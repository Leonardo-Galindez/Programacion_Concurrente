/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio4;

/**
 *
 * @author galin
 */
//Recurso compartido
public class Area {

    private int espacios;

    public Area(int espacios) {
        this.espacios = espacios;
    }

    public synchronized void modificarEspacios() {
        if (this.espacios != 0) {
            this.espacios = this.espacios - 1;
        }
    }

    public synchronized int getEspacios() {
        return this.espacios;
    }
}
