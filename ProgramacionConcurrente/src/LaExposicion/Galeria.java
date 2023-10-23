/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LaExposicion;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Galeria {

    private int kVisitantes = 5;
    private int cont = 0;

    //Metodos Visitantes
    public synchronized void ingresar() {
        if (cont < kVisitantes) {
            cont++;
        } else {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Galeria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
