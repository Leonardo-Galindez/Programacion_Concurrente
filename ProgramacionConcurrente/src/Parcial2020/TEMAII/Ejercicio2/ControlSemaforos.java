package Parcial2020.TEMAII.Ejercicio2;

public class ControlSemaforos implements Runnable {
    private GestorCruce gestorCruce;

    public ControlSemaforos(GestorCruce gestorCruce) {
        this.gestorCruce = gestorCruce;
    }

    public void run() {
        while (true) {
            espera();
            gestorCruce.cambiaSemaforos();
        }
    }

    public void espera() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
