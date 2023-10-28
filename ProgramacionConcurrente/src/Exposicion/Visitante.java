/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exposicion;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Visitante implements Runnable {

    private Museo museo;

    public Visitante(Museo museo) {
        this.museo = museo;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " quiere entrar al museo");
            museo.entrarVisitante();
            realizandoVisita();
            museo.salirVisitante();
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void realizandoVisita() {
        try {
            System.out.println(Thread.currentThread().getName() + " visitando....");
            Thread.sleep((new Random()).nextInt(1000) + 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
