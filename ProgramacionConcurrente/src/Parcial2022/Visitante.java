/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parcial2022;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Visitante implements Runnable {

    private Parque parque;

    public Visitante(Parque parque) {
        this.parque = parque;
    }

    public void run() {
        parque.entrarParque("V");
        simulacion();
        parque.salirParque("V");
    }

    public void simulacion() {
        try {
            Thread.sleep((new Random()).nextInt(1000) + 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(VisitanteLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
