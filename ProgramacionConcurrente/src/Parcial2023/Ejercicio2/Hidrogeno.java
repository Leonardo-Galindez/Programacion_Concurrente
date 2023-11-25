package Parcial2023.Ejercicio2;

public class Hidrogeno implements Runnable {

    private Agua agua;

    public Hidrogeno(Agua agua) {
        this.agua = agua;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            agua.hListo();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
