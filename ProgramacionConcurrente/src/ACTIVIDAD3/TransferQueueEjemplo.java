package ACTIVIDAD3;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class TransferQueueEjemplo {

    private TransferQueue<String> transferQueue;

    public TransferQueueEjemplo() {
        this.transferQueue = new LinkedTransferQueue<String>();
    }

    public void producir() throws InterruptedException {
        System.out.println("Productor"+ Thread.currentThread().getName()+" esperando");
        transferQueue.transfer(Thread.currentThread().getName());
        System.out.println("El productor " + Thread.currentThread().getName() + " produce");
    }

    public void consumir() throws InterruptedException {
        System.out.println("Consumidor esperando ");
        System.out.println("onsumiendo "+transferQueue.take());
        
    }
}
