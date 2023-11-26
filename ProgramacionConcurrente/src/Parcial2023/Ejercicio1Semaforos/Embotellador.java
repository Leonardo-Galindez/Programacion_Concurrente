package Parcial2023.Ejercicio1Semaforos;

public class Embotellador implements Runnable{
    private Planta planta;
    private String tipo;

    public Embotellador(Planta planta, String tipo) {
        this.planta = planta;
        this.tipo = tipo;
    }

    public void run() {
        while (true) {
            try {
                if (tipo.equals("V")) {
                    planta.guardarVino();
                } else {
                    planta.guardarAgua();
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
