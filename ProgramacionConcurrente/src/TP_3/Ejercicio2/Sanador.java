/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio2;

/**
 *
 * @author galin
 */
public class Sanador implements Runnable {

    private Energia energia;

    public Sanador(Energia energia) {
        this.energia = energia;
    }

    @Override
    public void run() {

        System.out.println("Sanador cura 3 unidades de Energia");
        energia.setEnergia(energia.ontenerEnergia() + 3);
    }
}
