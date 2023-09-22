/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioBarberoDurmiendo;

/**
 *
 * @author galin
 */
public class Main {
//politica demaforo(1,true);para el orden de bloqueo
    
    public static void main(String[] args) {

        Silla unaSilla = new Silla();

        Peluquero peluquero = new Peluquero(unaSilla);

        Thread[] clientes = new Thread[10];

        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Thread(new Cliente(unaSilla), "Cliente:" + i);
        }

        for (int i = 0; i < clientes.length; i++) {
            clientes[i].start();
        }
    }
}
