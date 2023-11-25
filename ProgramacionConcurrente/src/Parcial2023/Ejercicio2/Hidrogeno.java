package Parcial2023.Ejercicio2;

public class Hidrogeno implements Runnable {

    private Agua agua;

    public Hidrogeno(Agua agua) {
        this.agua = agua;
    }

    public void run() {
        try {
            agua.hListo();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
