package Parcial2020.TEMAI.Ejercicio1;

public class Auto implements Runnable {
    private ControlTrasnbordadorLock controlTrasbordador;

    public Auto(ControlTrasnbordadorLock controlTrasbordador) {
        this.controlTrasbordador = controlTrasbordador;
    }

    public void run() {

        controlTrasbordador.subirAutoLock();
        controlTrasbordador.bajarAutoLock();
    }
}
