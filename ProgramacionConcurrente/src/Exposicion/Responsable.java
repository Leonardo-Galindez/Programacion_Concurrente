/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exposicion;


import java.util.Random;

/**
 *
 * @author galin
 */
public class Responsable implements Runnable {

    private Museo museo;

    public Responsable(Museo museo) {
        this.museo = museo;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " quiere entrar al museo");
            museo.entrarResponsable();
            realizandoControl();
            museo.salirResponsable();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void realizandoControl() {
        try {
            System.out.println(Thread.currentThread().getName() + " visitando....");
            Thread.sleep((new Random()).nextInt(1000) + 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
