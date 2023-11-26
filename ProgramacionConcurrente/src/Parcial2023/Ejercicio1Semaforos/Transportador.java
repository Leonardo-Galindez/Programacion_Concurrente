package Parcial2023.Ejercicio1Semaforos;

public class Transportador implements Runnable {
    private Planta planta;

    public Transportador(Planta planta) {
        this.planta = planta;
    }

    public void run() {
        while (true) {
            try {
                planta.transportarCajas();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
