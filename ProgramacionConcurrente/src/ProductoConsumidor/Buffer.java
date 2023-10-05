/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProductoConsumidor;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Buffer {

    private Semaphore consumirProducto;
    private Semaphore ponerProducto;

    public Buffer() {
        this.consumirProducto = new Semaphore(0);
        this.ponerProducto = new Semaphore(10);
    }

    public void ponerProducto() {
        try {
            ponerProducto.acquire();
            consumirProducto.release();
            System.out.println("Pone producto");
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consumirProducto() {
        try {
            consumirProducto.acquire();
            ponerProducto.release();
            System.out.println("Consume producto");
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
