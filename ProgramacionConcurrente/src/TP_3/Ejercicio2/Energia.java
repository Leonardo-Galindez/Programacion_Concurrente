/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio2;

/**
 *
 * @author galin
 */
public class Energia {

    private int valor;

    public Energia(int valor) {
        this.valor = valor;
    }

    //Exclusion mutua
    //lock de sincrinizado
    public synchronized int ontenerEnergia() {
        return this.valor;
    }

    public synchronized void modificarEnergia(int modificacion) {
        this.valor = this.valor + modificacion;
    }
}
