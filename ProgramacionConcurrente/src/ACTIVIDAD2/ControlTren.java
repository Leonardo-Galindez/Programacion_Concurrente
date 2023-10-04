package ACTIVIDAD2;

public class ControlTren implements Runnable {
    private Tren tren;

    public ControlTren(Tren tren) {
        this.tren = tren;
    }

    @Override
    public void run() {
        while (true) {
            tren.iniciarViaje();
            viaje();
            tren.finalizarViaje();
        }
    }

    private void viaje() {
        try {
            System.out.println("Viajando......");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
