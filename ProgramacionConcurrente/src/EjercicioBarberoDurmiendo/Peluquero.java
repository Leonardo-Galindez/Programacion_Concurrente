/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioBarberoDurmiendo;

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
            laSilla.esperarProximoCliente();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            laSilla.finalizarCorte();
        }

    }
}
