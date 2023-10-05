package ACTIVIDAD2;

import java.util.Random;

public class Pasajero implements Runnable {

    private Tren tren;

    public Pasajero(Tren tren) {
        this.tren = tren;
    }

    @Override
    public void run() {
        tren.subirTren();
        
        tren.bajarTren();
    }

    public void esperar() {
        try {
            System.out.println(Thread.currentThread().getName() + " esta esperando");
            Thread.sleep((new Random()).nextInt(1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
