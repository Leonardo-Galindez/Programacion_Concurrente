/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BarberoDurmiendo;

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
            esperar();
            laSilla.finalizarCorte();
        }
    }

    public void esperar() {
        try {
            Thread.sleep(1000);
            System.out.println("...........Cortando.....el....pelo........");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
