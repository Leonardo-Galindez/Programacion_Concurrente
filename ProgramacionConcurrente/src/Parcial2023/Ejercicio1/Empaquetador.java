package Parcial2023.Ejercicio1;

public class Empaquetador implements Runnable {

    private Planta planta;

    public Empaquetador(Planta planta) {
        this.planta = planta;
    }

    public void run() {
        while (true) {
            planta.empaquetarCaja();
            planta.reponerCaja();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
