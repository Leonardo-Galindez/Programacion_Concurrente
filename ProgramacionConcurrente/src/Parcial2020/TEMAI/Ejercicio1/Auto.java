package Parcial2020.TEMAI.Ejercicio1;

public class Auto implements Runnable {
    private ControlTrasbordador controlTrasbordador;

    public Auto(ControlTrasbordador controlTrasbordador) {
        this.controlTrasbordador = controlTrasbordador;
    }

    public void run() {
        try {
            controlTrasbordador.subirAuto();
            controlTrasbordador.bajarAuto();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
