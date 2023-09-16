/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio4;

/**
 *
 * @author galin
 */
public class Visitante implements Runnable {

    private String nombre;
    private Area area;

    public Visitante(String nombre, Area area) {
        this.nombre = nombre;
        this.area = area;
    }

    public void reservarArea(boolean estado) {
        if (this.area.getEspacios() != 0) {
            area.modificarEspacios();
            System.out.println("El area se reservo con exito");
        } else {
            System.out.println("El area no esta disponible");
        }
    }

    @Override
    public void run() {
        try {
            this.reservarArea(true);
            Thread.sleep(1000);
            this.reservarArea(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
