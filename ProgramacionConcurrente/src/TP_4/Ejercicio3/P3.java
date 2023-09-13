/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_4.Ejercicio3;

/**
 *
 * @author galin
 */
public class P3 implements Runnable {

    private String nombre;
    private Mensaje mensaje;

    public P3(Mensaje mensaje) {
        this.mensaje = mensaje;
        this.nombre = "P3";
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
