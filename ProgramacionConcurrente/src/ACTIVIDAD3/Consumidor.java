package ACTIVIDAD3;

import java.util.concurrent.TransferQueue;

public class Consumidor implements Runnable {

    private TransferQueueEjemplo cola;

    public Consumidor(TransferQueueEjemplo cola) {
        this.cola = cola;
    }

    @Override
    public void run() {

    }

}
