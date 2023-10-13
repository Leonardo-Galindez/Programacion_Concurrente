/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio1;

/**
 *
 * @author galin
 */
public class Persona implements Runnable {

    private Piscina piscina;

    public Persona(Piscina piscina) {
        this.piscina = piscina;
    }

    public void run() {

        piscina.ingresarPiscina();
        bañando();
        piscina.salirPiscina();

    }
    
    public void bañando() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
