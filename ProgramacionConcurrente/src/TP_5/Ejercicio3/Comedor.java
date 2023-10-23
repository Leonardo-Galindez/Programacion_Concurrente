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
    private String tipo;
    private Semaphore turno;
    private Semaphore comedores;
    private Semaphore mutexEspecie;

    //private int comedores = 2;//cantidad de comedores
    private int cont = 0;

    private int max = 3;//maximo animales mismo tipo
    private int contPerrosComiendo = 0;//
    private int contGatosComiendo = 0;
    private int contPerrosEsperando = 0;
    private int contGatosEsperando = 0;

    public Comedor() {
        this.turno = new Semaphore(1);
        this.comedores = new Semaphore(3);
        this.mutexEspecie = new Semaphore(1);//sirve para el contador de seccion critica
    }

    //Metodos Perro
    //metodo para saber cuantos animales de cada especie quieren comer
    public void ingresarComedor() {
        try {
            mutexEspecie.acquire();
            if (Thread.currentThread().getName().equals("P")) {
                //liberamos los perros 
                contPerrosEsperando++;
            } else {
                //liberamos los gatos
                contGatosEsperando++;
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void turno() {
        //solo lo ejecutamos una vez para saber el primer animal que entra
        turno.tryAcquire();
        if (Thread.currentThread().getName().equals("P")) {
            tipo = "P";
        } else {
            tipo = "G";
        }
    }

    public void comerPerro() {
        try {
            comedores.acquire();
            mutexEspecie.acquire();
            if (tipo.equals("P")) {
                cont++;
                contPerrosComiendo++;
                contPerrosEsperando--;
                System.out.println("El perro " + Thread.currentThread().getName() + " empezo a comer");
                if (contPerrosComiendo == max) {
                    //cambio de prioridad
                    if (contGatosEsperando != 0) {
                        tipo = "G";
                    } else {
                        //recetiamos porque no hay gatos esperando 
                        contPerrosComiendo = 0;
                    }
                }
            } else {
                //si es gato libera porque estan los perros
                comedores.release();
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finalizarComerPerro() {
        try {
            System.out.println(cont);
            mutexEspecie.acquire();
            if (cont != 0) {
                System.out.println("El perro " + Thread.currentThread().getName() + " termino de comer");
                cont--;
                if (cont == 0) {
                    //liberamos todos los comedores y los permisos de los gatos para que pueden comer
                    comedores.release(3);
                   
                }
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo Gato
    public void comerGato() {
        try {
            comedores.acquire();
            mutexEspecie.acquire();
            if (tipo.equals("G")) {
                cont++;
                contGatosComiendo++;
                contGatosEsperando--;
                System.out.println("El gato " + Thread.currentThread().getName() + " empezo a comer");
                if (contGatosComiendo == max) {
                    //cambio de prioridad
                    if (contPerrosEsperando != 0) {
                        tipo = "P";
                    } else {
                        //recetiamos porque no hay gatos esperando 
                        contGatosComiendo = 0;
                    }
                }
            } else {
                comedores.release();
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finalizarComerGato() {
        try {
            System.out.println(cont);
            mutexEspecie.acquire();
            if (cont != 0) {
                System.out.println("El gato " + Thread.currentThread().getName() + " termino de comer");
                cont--;
                if (cont == 0) {
                    //liberamos todos los comedores y los permisos de los gatos para que pueden comer
                    comedores.release(3);                  
                }
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
