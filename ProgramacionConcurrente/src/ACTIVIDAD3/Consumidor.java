package ACTIVIDAD3;

import java.util.concurrent.TransferQueue;

public class Consumidor implements Runnable {

    private TransferQueueEjemplo cola;

    public Consumidor(TransferQueueEjemplo cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        while (true) {
           try {
                cola.consumir();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
