/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio3;

/**
 *
 * @author galin
 */
public class Jaula {

    private Hamaca hamaca;
    private Rueda rueda;
    private PlatoComida platoComida;

    public Jaula(Hamaca hamaca, Rueda rueda, PlatoComida platoComida) {
        this.hamaca = hamaca;
        this.platoComida = platoComida;
        this.rueda = rueda;
    }

    public synchronized void comerComida() {
        System.out.println("El " + Thread.currentThread().getName() + " esta comiendo");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El " + Thread.currentThread().getName() + " termino de comer");
    }

    public synchronized void usarHamaca() {
        System.out.println("El " + Thread.currentThread().getName() + " esta usando la hamaca");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El " + Thread.currentThread().getName() + " termino de usar la hamaca");
    }

    public synchronized void usarRueda() {
        System.out.println("El " + Thread.currentThread().getName() + " esta usando la rueda");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El " + Thread.currentThread().getName() + " termino de usar la rueda");
    }

}
