package Parcial2023.Ejercicio1Semaforos;

public class Empaquetador {

    private Planta planta;

    public Empaquetador(Planta planta) {
        this.planta = planta;
    }

    public void run() {
        while (true) {
            try {
                planta.empaquetarCaja();
                Thread.sleep(1000);
                planta.reponerCaja();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
