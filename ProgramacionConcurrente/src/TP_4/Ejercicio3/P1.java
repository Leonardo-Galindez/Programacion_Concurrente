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
public class P1 implements Runnable {

    private String nombre;
    private Mensaje mensaje;
    private Semaphore sem1;
    //consultar si los semaforo van en los procesos o solo en el recurso compartido

    public P1(Mensaje mensaje) {
        this.mensaje = mensaje;
        this.nombre = "P1";
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        this.mensaje.toString(this.nombre);
    }
}
