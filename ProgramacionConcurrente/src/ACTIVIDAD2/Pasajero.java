package ACTIVIDAD2;

import java.util.Random;

public class Pasajero implements Runnable {

    private Tren tren;

    public Pasajero(Tren tren) {
        this.tren = tren;
    }

    @Override
    public void run() {
        boolean subio = false;
        int numAsiento = -1;
        while (!subio) {
            numAsiento = tren.subirTren();
            if (numAsiento == -1) {
                esperar();
            } else {
                subio = true;
            }
        }
        tren.bajarTren(numAsiento);
    }

    public void esperar() {
        try {
            System.out.println(Thread.currentThread().getName()+" esta espeRANDO");
            Thread.sleep((new Random()).nextInt(1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
