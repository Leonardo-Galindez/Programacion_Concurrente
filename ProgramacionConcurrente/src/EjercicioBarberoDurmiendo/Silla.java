/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioBarberoDurmiendo;

import java.util.concurrent.Semaphore;

/**
 *
 * @author galin
 */
public class Silla {

    private Semaphore silla;
    private Semaphore corte;
    private Semaphore salida;

    public Silla() {
        this.silla = new Semaphore(1, true);
        this.corte = new Semaphore(0);//para decir si el barbero esta durmiendo
        this.salida = new Semaphore(0);//
    }

    public void verificarSillon() throws InterruptedException {
        this.silla.acquire();
    }

    public void tomarSilla() {
        try {
            this.silla.acquire();
            System.out.println("Silla Solicitado por:" + Thread.currentThread().getName());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void solicitarAtencion() {
        this.corte.release();
        System.out.println("Corte pedido");
    }

    public void iniciarCorte() throws InterruptedException {
       
        this.corte.acquire();
        
    }

    public void finalizarCorte() {
        this.corte.release();
        System.out.println("Terminamos corte");
        this.salida.release();
    }

    public void esperarAtencion() {
        try {
            this.salida.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void liberarSilla() {
        this.silla.release();
        System.out.println("Silla libre");
    }

    public void esperarProximoCliente() {
        try {
            this.corte.acquire();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
