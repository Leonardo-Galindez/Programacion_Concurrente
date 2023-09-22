/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EjercicioBarberoDurmiendo;

/**
 *
 * @author galin
 */
public class Cliente implements Runnable {

    private Silla laSilla;
    private String Nombre;

    public Cliente(Silla laSilla) {
        this.laSilla = laSilla;
        this.Nombre = Nombre;
    }

    public void run() {
        //laSilla.verificarSillon();
        laSilla.solicitarAtencion();
        laSilla.esperarAtencion();
        laSilla.liberarSilla();
    }
}
