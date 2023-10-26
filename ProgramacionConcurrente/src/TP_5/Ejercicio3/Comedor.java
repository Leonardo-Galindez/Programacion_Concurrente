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

    private Semaphore perros;
    private Semaphore gatos;
    private Semaphore mutexEspecie;

    private int cantComedores = 12;//cantidad de comedores disponibles
    private int max = 15;//maximo animales mismo tipo que comieron

    private int contPerrosEsperando = 0;
    private int contPerrosComiendo = 0;
    private int contPerrosComieron = 0;

    private int contGatosEsperando = 0;
    private int contGatosComiendo = 0;
    private int contGatosComieron = 0;

    public Comedor() {
        this.mutexEspecie = new Semaphore(1);//sirve para la exclusion mutua 
        this.perros = new Semaphore(cantComedores);//cantidad de comedores disponibles para perros
        this.gatos = new Semaphore(cantComedores);//cantidad de comedores disponibles para gatos
    }

    //Metodo de gato y perro
    public void ingresarComedor(String unTipo) {
        try {
            mutexEspecie.acquire();
            //primera vez
            if (contPerrosEsperando == 0 && contGatosEsperando == 0
                    && contGatosComiendo == 0 && contPerrosComiendo == 0) {
                if (unTipo.equals("P")) {
                    //tomamos los permisos de los gatos
                    gatos.acquire(cantComedores);
                } else {
                    //tomamos los permisos de los perros
                    perros.acquire(cantComedores);
                }
            }
            //contamos la cantidad de perros y gatos esperando
            if (unTipo.equals("P")) {
                contPerrosEsperando++;
            } else {
                contGatosEsperando++;
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodos de Perro
    public void comerPerro(String unTipo) {
        try {
            perros.acquire();
            mutexEspecie.acquire();
            System.out.println("El perro " + Thread.currentThread().getName() + " esta comiendo");
            contPerrosEsperando--;
            contPerrosComiendo++;
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finalizarComerPerro() {
        try {
            mutexEspecie.acquire();
            System.out.println("El perro " + Thread.currentThread().getName() + " termino de comer");
            contPerrosComiendo--;
            contPerrosComieron++;
            if (contPerrosComiendo == 0) {
                if (contPerrosComieron == max && contGatosEsperando != 0) {
                    //cambio de prioridad
                    //liberamos permisos de gatos
                    contPerrosComieron = 0;
                    gatos.release(cantComedores);
                } else {
                    if (contGatosEsperando != 0 && contPerrosEsperando == 0) {
                        //liberamos permisos de gatos
                        gatos.release(cantComedores);
                    } else {
                        if (max % cantComedores != 0) {
                            //obtenesmos el resto para saber cuanto liberar 
                            perros.release(max % cantComedores);
                        } else {
                            perros.release(cantComedores);
                        }
                    }
                }
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comerGato(String unTipo) {
        try {
            gatos.acquire();
            mutexEspecie.acquire();
            System.out.println("El gato " + Thread.currentThread().getName() + " esta comiendo");
            contGatosEsperando--;
            contGatosComiendo++;
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finalizarComerGato() {
        try {
            mutexEspecie.acquire();
            System.out.println("El gato " + Thread.currentThread().getName() + " termino de comer");
            contGatosComiendo--;
            contGatosComieron++;
            if (contGatosComiendo == 0) {
                if (contGatosComieron == max && contPerrosEsperando != 0) {
                    //cambio de prioridad
                    //liberamos permisos de peros
                    contGatosComieron = 0;
                    perros.release(cantComedores);
                } else {
                    if (contPerrosEsperando != 0 && contGatosEsperando == 0) {
                        //liberamos permisos de perros
                        perros.release(cantComedores);
                    } else {
                        if (max % cantComedores != 0) {
                            //obtenesmos el resto para saber cuanto liberar 
                            gatos.release(max % cantComedores);
                        } else {
                            gatos.release(cantComedores);
                        }
                    }
                }
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
