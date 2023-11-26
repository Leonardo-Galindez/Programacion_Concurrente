

public class Transportador implements Runnable {

    private Planta planta;

    public Transportador(Planta planta) {
        this.planta = planta;
    }

    public void run() {
        while (true) {
            planta.transportarCajas();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
