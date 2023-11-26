package Parcial2023.Ejercicio2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Agua {

    private Lock accesoHidrogeno;
    private Lock accesoOxigeno;

    private Condition hidrogeno;
    private Condition oxigeno;

    private int contHidrogeno = 0;
    private int limiteRecipiente;
    private int contAguas;

    private boolean oListo = false;
    private boolean hListo = false;
    private boolean haciendoAgua = false;

    public Agua(int limite) {

        this.accesoHidrogeno = new ReentrantLock();
        this.accesoOxigeno = new ReentrantLock();

        this.hidrogeno = accesoHidrogeno.newCondition();
        this.oxigeno = accesoOxigeno.newCondition();

        this.limiteRecipiente = limite;
        this.contAguas = 0;

    }

    public void hListo() {
        try {
            accesoHidrogeno.lock();
            while (contHidrogeno >= 2) {
                hidrogeno.await();
            }
            System.out.println("El " + Thread.currentThread().getName() + " esta listo");
            contHidrogeno++;
            if (contHidrogeno == 2) {
                hListo = true;
            }
            if (hListo && oListo && !haciendoAgua) {
                System.out.println("El " + Thread.currentThread().getName() + " mando hacer agua");
                haciendoAgua = true;
                this.hacerAgua();      
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            accesoHidrogeno.unlock();
        }
    }

    public void oListo() {
        try {
            accesoOxigeno.lock();
            while (oListo) {
                oxigeno.await();
            }
            System.out.println("El " + Thread.currentThread().getName() + " esta listo");
            oListo = true;
            if (hListo && oListo && !haciendoAgua) {
                System.out.println("El " + Thread.currentThread().getName() + " mando hacer agua");
                haciendoAgua = true;
                this.hacerAgua();        
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            accesoOxigeno.unlock();
        }
    }

    private void hacerAgua() {
        try {
            //esta bien esto???
            accesoHidrogeno.lock();
            accesoOxigeno.lock();
            System.out.println("SE CREO AGUA !!!");
            contAguas++;
            if (contAguas == limiteRecipiente) {
                System.out.println("EL AGUA ESTA LISTA PARA ENVASARLA !!!");
                contAguas = 0;
            }
            contHidrogeno = 0;
            oListo = false;
            hListo = false;
            haciendoAgua = false;
            hidrogeno.signalAll();
            oxigeno.signalAll();
        } finally {
            accesoHidrogeno.unlock();
            accesoOxigeno.unlock();
        }
    }

}
