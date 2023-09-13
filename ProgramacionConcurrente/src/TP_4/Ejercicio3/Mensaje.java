/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio3;

import java.util.concurrent.Semaphore;

/**
 *
 * @author galin
 */
public class Mensaje {

    private String mensaje;
    private Semaphore semaforo;

    public Mensaje() {
        this.mensaje = "Estamos en el Proceso";;
        this.semaforo = new Semaphore(1);
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void toString(String proceso) {
        try {
            semaforo.acquire();
            System.out.println(this.mensaje + " " + proceso);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //pase lo que pase el semaforo se libera
            semaforo.release();
        }
    }
}
