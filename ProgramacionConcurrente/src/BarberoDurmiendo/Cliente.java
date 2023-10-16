/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BarberoDurmiendo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Cliente implements Runnable {

    private Silla laSilla;

    public Cliente(Silla laSilla) {
        this.laSilla = laSilla;
    }

    public void run() {
        laSilla.verificarSillon();
        laSilla.solicitarAtencion();
        laSilla.esperarAtencion();
        laSilla.liberarSilla();
    }
}
