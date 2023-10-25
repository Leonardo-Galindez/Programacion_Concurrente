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

    private int cont = 0;//contador para finalizar de comer
    private int cantComedores = 3;
    private int max = 4;//maximo animales mismo tipo que comieron
    private int auxMax = max;
    private String tipo;// tipos de la especie con el turno

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
                tipo = unTipo;
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
                //tendria que agreagar un contador para el contEsperando para comer y tro para entrar ??
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
            auxMax--;//vamos restanto el maximo permitido
            cont++;//cantidad de perros que estan comiendo para el release
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
                    gatos.release(cantComedores);
                } else {
                    if (contGatosEsperando != 0 && contPerrosEsperando == 0) {
                        //liberamos permisos de gatos
                        gatos.release(cantComedores);
                    } else {
                        //liberamos los perror que estaban comiendo que puede no ser la cantidad de comedores
                        perros.release(auxMax);
                        auxMax = max;
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
            auxMax--;
            cont++;//cantidad de gatos que estan comiendo para el release
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
            if (contPerrosComiendo == 0) {
                if (contGatosComieron == max && contPerrosEsperando != 0) {
                    //cambio de prioridad
                    //liberamos permisos de gatos
                    perros.release(cantComedores);
                } else {
                    if (contPerrosEsperando != 0 && contGatosEsperando == 0) {
                        //liberamos permisos de gatos
                        perros.release(cantComedores);
                    } else {
                        //liberamos los gatos que estaban comiendo que puede no ser la cantidad de comedores
                        gatos.release(auxMax);
                        auxMax = max;
                    }
                }
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
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
     */
}
