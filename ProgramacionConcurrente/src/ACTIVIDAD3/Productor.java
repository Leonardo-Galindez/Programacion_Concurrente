package ACTIVIDAD3;

import java.util.concurrent.TransferQueue;

public class Productor implements Runnable {

    private TransferQueueEjemplo cola;

    public Productor(TransferQueueEjemplo cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
       
    }

}
