package ACTIVIDAD2;

import java.util.concurrent.Semaphore;

public class Asiento {
    private boolean ocupado;
    private Semaphore semOcupado;

    public Asiento() {
        ocupado = false;
        semOcupado = new Semaphore(1);
    }

    public boolean ocupar() {
        boolean ocupa2=ocupado;
        if (!ocupa2) {
            try {
                ocupado = true;
                semOcupado.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ocupa2;
    }

    public void liberar() {
        ocupado = false;
        semOcupado.release();
    }
}
