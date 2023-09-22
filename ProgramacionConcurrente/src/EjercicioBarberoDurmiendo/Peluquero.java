/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioBarberoDurmiendo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Peluquero implements Runnable {

    private Silla laSilla;

    public Peluquero(Silla laSilla) {
        this.laSilla = laSilla;
    }

    public void run() {
        while (true) {
            try {
                laSilla.esperarProximoCliente();

                Thread.sleep(100);

                laSilla.finalizarCorte();
            } catch (InterruptedException ex) {
                Logger.getLogger(Peluquero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
