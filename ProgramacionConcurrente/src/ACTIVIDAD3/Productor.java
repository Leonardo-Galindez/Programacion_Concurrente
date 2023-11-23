package ACTIVIDAD3;

import java.util.concurrent.TransferQueue;

public class Productor implements Runnable {

    private TransferQueueEjemplo cola;

    public Productor(TransferQueueEjemplo cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        int i = 0;
        while (i != 3) {
            try {
                cola.producir();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            i++;
        }
    }

}
