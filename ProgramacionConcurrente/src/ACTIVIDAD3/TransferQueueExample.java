package ACTIVIDAD3;

public class TransferQueueExample {

    /**
     * @param args
     */
    public static void main(String[] args) {
        TransferQueue transferQueue;

        // Productor
        new Thread(() -> {
            try {
                String message = "Hola, soy un mensaje de transferencia.";
                System.out.println("Productor: Transfiriendo - " + message);
                transferQueue.transfer(message);
                System.out.println("Productor: Transferencia completa.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Consumidor
        new Thread(() -> {
            try {
                System.out.println("Consumidor: Esperando para recibir...");
                String receivedMessage = transferQueue.take();
                System.out.println("Consumidor: Mensaje recibido - " + receivedMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
