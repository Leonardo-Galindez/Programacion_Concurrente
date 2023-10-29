/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author galin
 */
public class Nodo {

    private Object elem;
    private Nodo enlace;

    //Constructor
    public Nodo(Object unElem, Nodo unEnlace) {

        this.elem = unElem;
        this.enlace = unEnlace;
    }

    //Observadores
    public Object getElem() {
        return this.elem;
    }

    public Nodo getEnlace() {
        return this.enlace;
    }

    //Modificadores
    public void setElem(Object unElem) {
        this.elem = unElem;
    }

    public void setEnlace(Nodo nuevoEnlace) {
        this.enlace = nuevoEnlace;
    }
}
