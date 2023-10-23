/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_5.Ejercicio3;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galin
 */
public class Comedor {

    //private Semaphore cantidad;
    private Semaphore gatos;
    private Semaphore perros;
    private Semaphore mutexEspecie;

    private int comedores = 2;//cantidad de comedores
    private int cont = 0;

    private int max = 5;
    private int contPerrosComiendo = 0;//
    private int contGatosComiendo = 0;
    private int contPerrosEsperando = 0;
    private int contGatosEsperando = 0;

    public Comedor() {
        this.gatos = new Semaphore(2);//sirve para indircar si el gato puede comer
        this.perros = new Semaphore(2);//sirve para indircar si el perro puede comer
        this.mutexEspecie = new Semaphore(1);//sirve para el contador de seccion critica
    }

    //Metodos Perro
    public void ingresarComedorPerro() {
        try {
            mutexEspecie.acquire();
            contPerrosEsperando++;
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comerPerro() {
        try {
            perros.acquire();
            mutexEspecie.acquire();
            if (cont < comedores) {
                cont++;
                if (cont == 1) {
                    //adquirimos todos los permisos de gato para que pueda comer
                    //verificar si hay gatos esperando
                    if (contGatosEsperando != 0) {
                        gatos.acquire(2);
                    }
                }
                System.out.println("El perro " + Thread.currentThread().getName() + " empezo a comer");
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finalizarComerPerro() {
        try {
            mutexEspecie.acquire();
            if (cont != 0) {
                System.out.println("El perro " + Thread.currentThread().getName() + " termino de comer");
                cont--;
                if (cont == 0) {
                    //liberamos todos los comedores y los permisos de los gatos para que pueden comer
                    gatos.release(2);
                    //perros.release(2);
                    //mutexEspecie.release();
                }
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo Gato
    public void ingresarComedorGatos() {
        try {
            mutexEspecie.acquire();
            contGatosEsperando++;
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comerGato() {
        try {
            gatos.acquire();
            mutexEspecie.acquire();
            if (cont <= comedores) {
                cont++;
                if (cont == 1) {
                    //adquirimos todos los permisos de perro para que pueda comer
                    perros.acquire(2);
                }
                System.out.println("El gato " + Thread.currentThread().getName() + " empezo a comer");
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finalizarComerGato() {
        try {
            mutexEspecie.acquire();
            if (cont != 0) {
                System.out.println("El perro " + Thread.currentThread().getName() + " termino de comer");
                cont--;
                if (cont == 0) {
                    //liberamos todos los comedores y los permisos de los gatos para que pueden comer
                    perros.release(2);
                }
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
