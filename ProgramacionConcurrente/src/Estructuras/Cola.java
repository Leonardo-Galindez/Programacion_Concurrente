/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author galin
 */
public class Cola {

    //Atributos
    private Nodo frente;
    private Nodo fin;

    //Constructor
    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    //Operaciones 
    public synchronized boolean poner(Object elem) {
        boolean exito = false;
        Nodo nuevoNodo = new Nodo(elem, null);//Nodo solo con elem
        //caso especial
        if (this.frente == null && this.fin == null) {
            this.frente = nuevoNodo;
            this.fin = nuevoNodo;
            exito = true;
        } else {
            //nodo fin enlazada con nuevo nodo
            this.fin.setEnlace(nuevoNodo);
            //fin enlazado con nuevo nodo
            this.fin = nuevoNodo;
            exito = true;
        }

        return exito;
    }

    public synchronized boolean sacar() {
        boolean exito = true;
        if (this.frente == null) {
            exito = false;//la cola esta vacia
        } else {
            //hay un elemento
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }
        return exito;
    }

    public synchronized void vaciar() {
        //todos los nodos son recogidos por el Garbage collector
        this.frente = null;
        this.fin = null;
    }

    public synchronized Object obtenerFrente() {
        Object elem = null;
        if (!esVacia()) {
            elem = this.frente.getElem();
        }
        return elem;
    }

    public synchronized Cola clone() {
        Cola clonCola = new Cola();
        Nodo aux1, aux2;
        if (this.frente != null) {
            aux1 = new Nodo(this.frente.getElem(), null);
            clonCola.frente = aux1;
            clonCola.fin = aux1;
            aux2 = this.frente.getEnlace();
            while (aux2 != null) {
                Nodo aux = new Nodo(aux2.getElem(), null);
                aux1.setEnlace(aux);
                clonCola.fin = aux;
                aux1 = aux1.getEnlace();
                aux2 = aux2.getEnlace();
            }
        }
        return clonCola;
    }

    public synchronized boolean esVacia() {
        boolean vacia = true;
        if (this.frente != null && this.fin != null) {
            vacia = false;
        }
        return vacia;

    }

    public synchronized String toString() {
        String cadena = "";
        if (this.frente == null && this.fin == null) {
            cadena = "Cola Vacia";
        } else {
            Nodo nodoFrente = this.frente;
            cadena = "[";
            while (nodoFrente != null) {
                cadena += nodoFrente.getElem().toString();
                nodoFrente = nodoFrente.getEnlace();
                if (nodoFrente != null) {
                    cadena += ",";
                }
            }
            cadena += "]";

        }
        return cadena;
    }
}
