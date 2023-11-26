package Parcial2023.Ejercicio2Semaforos;

public class Oxigeno implements Runnable {
    private Agua agua;

    public Oxigeno(Agua agua) {
        this.agua = agua;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            agua.oListo();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
