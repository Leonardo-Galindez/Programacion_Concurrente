package Parcial2020.TEMAII.Ejercicio2;

public class ControlSemaforos implements Runnable {
    private GestorCruceSemaforo gestorCruce;

    public ControlSemaforos(GestorCruceSemaforo gestorCruce) {
        this.gestorCruce = gestorCruce;
    }

    public void run() {
        while (true) {
            try {
                gestorCruce.cambiarSemaforos();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            espera();
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
