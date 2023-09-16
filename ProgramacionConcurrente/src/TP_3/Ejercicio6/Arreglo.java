/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio6;

/**
 *
 * @author galin
 */
public class Arreglo {

    private int[] arreglo;

    public Arreglo(int[] arreglo) {
        this.arreglo = arreglo;
    }

    public synchronized int[] getArreglo() {
        return arreglo;
    }

    public synchronized void setArreglo(int[] arreglo) {
        this.arreglo = arreglo;
    }

}
