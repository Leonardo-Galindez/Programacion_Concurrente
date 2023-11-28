package Parcial2023.Ejercicio1Semaforos;

public class Embotellador implements Runnable {
    private Planta planta;
    private String tipo;

    public Embotellador(Planta planta, String tipo) {
        this.planta = planta;
        this.tipo = tipo;
    }

    public void run() {

        try {
            
            if (tipo.equals("V")) {
                while (true) {
                    planta.guardarVino();
                }
            } else {
                while (true) {
                    planta.guardarAgua();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
