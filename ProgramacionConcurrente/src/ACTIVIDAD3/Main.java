package ACTIVIDAD3;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class Main {
    public static void main(String[] args) {

        TransferQueueEjemplo transferQueue = new TransferQueueEjemplo();

        Thread hiloProductor = new Thread(new Productor(transferQueue));
        Thread hiloConsumidor = new Thread(new Consumidor(transferQueue));

        hiloProductor.start();
        hiloConsumidor.start();
    }
}
