package ACTIVIDAD2;

public class Main {

    public static void main(String[] args) {

        Tren tren = new Tren(5);//creamos un tren con 5 acientos

        Thread pasajeros[] = new Thread[10];
        Thread control = new Thread(new ControlTren(tren), " CONTROL ");
        
        for (int i = 0; i < pasajeros.length; i++) {
            pasajeros[i] = new Thread(new Pasajero(tren), "Pasajero " + (i + 1));
        }
        control.start();
        for (int i = 0; i < pasajeros.length; i++) {
            pasajeros[i].start();
        }

    }
}
