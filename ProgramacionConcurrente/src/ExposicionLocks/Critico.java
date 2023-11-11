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
public class Critico implements Runnable {

    private Museo museo;

    public Critico(Museo museo) {
        this.museo = museo;
    }

    public void run() {
        visitando();
        museo.entrarCritico();
        visitando();
        museo.salirCritico();
    }

    public void visitando() {
        try {
            Thread.sleep((new Random().nextInt(1000) + 1000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
