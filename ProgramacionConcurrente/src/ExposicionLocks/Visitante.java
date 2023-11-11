/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExposicionLocks;

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
        museo.entrarVisitante();
        visitando();
        museo.salirVisitante();
    }

    public void visitando() {
        try {
            Thread.sleep((new Random().nextInt(1000) + 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
