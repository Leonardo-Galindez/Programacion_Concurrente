package ACTIVIDAD3;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class Main {
    public static void main(String[] args) {

        TransferQueueEjemplo transferQueue = new TransferQueueEjemplo();

        Thread hiloProductor = new Thread(new Productor(transferQueue),"-1");
        Thread hiloProductor1 = new Thread(new Productor(transferQueue),"-2");
        Thread hiloProductor2 = new Thread(new Productor(transferQueue),"-3");

        Thread hiloConsumidor = new Thread(new Consumidor(transferQueue));

        hiloProductor.start();
        hiloProductor1.start();
        hiloProductor2.start();
        hiloConsumidor.start();
    }
}
