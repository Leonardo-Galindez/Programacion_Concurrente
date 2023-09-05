/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio4;

/**
 *
 * @author galin
 */
//Recurso compartido
public class Area {

    private boolean estado;

    public Area() {
        this.estado = false;
    }

    public synchronized void modificarEstado(boolean estado) {
        this.estado = estado;
    }
    
    public boolean getEstado(){
        return this.estado;
    }
}
