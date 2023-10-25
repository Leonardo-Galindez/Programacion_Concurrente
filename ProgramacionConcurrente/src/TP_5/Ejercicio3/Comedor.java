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

    private Semaphore comedores;
    private Semaphore mutexEspecie;

    private int cont = 1;//contador para finalizar de comer
    private int cantComedores = 3;
    private int max = 3;//maximo animales mismo tipo que comieron
    private String tipo;// tipos de la especie con el turno

    private int contPerrosEsperando = 0;
    private int contPerrosComiendo = 0;
    private int contPerrosComieron = 0;

    private int contGatosEsperando = 0;
    private int contGatosComiendo = 0;
    private int contGatosComieron = 0;

    public Comedor() {
        this.comedores = new Semaphore(cantComedores);//cantidad de comedores disponibles
        this.mutexEspecie = new Semaphore(1);//sirve para la exclusion mutua 
        //tendria que entrar por parametros la cantidad de perros y gatos que quieren comer??
    }

    //Metodo de gato y perro
    //Metodo para verificar el tipo del animal y contarlos
    public void ingresarComedor(String unTipo) {
        try {
            mutexEspecie.acquire();
            //primera vez
            if (contPerrosEsperando == 0 && contGatosEsperando == 0
                    && contGatosComiendo == 0 && contPerrosComiendo == 0) {
                //definimos el tipo del primer animal que entra
                if (unTipo.equals("P")) {
                    tipo = "P";
                } else {
                    tipo = "G";
                }
            }
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

    //CONSULTAR COMO HACER EL CAMBIO DE PRIORIDAD
    //Metodos de Perro
    public void comerPerro(String unTipo) {
        try {
            comedores.acquire();
            mutexEspecie.acquire();
            if (tipo.equals("P")) {
                if (contPerrosEsperando != 0) {
                    System.out.println("El perro " + Thread.currentThread().getName() + " esta comiendo");
                    contPerrosEsperando--;
                    contPerrosComiendo++;
                    contPerrosComieron++;
                    cont++;
                    if (contPerrosComieron == max) {
                        //cambio de prioridad
                        tipo = "G";
                    }
                } else {
                    if (contGatosEsperando != 0) {
                        //cambio de prioridad
                        tipo = "G";
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

    public void finalizarComerPerro() {
        try {
            mutexEspecie.acquire();
            if (contPerrosComiendo != 0) {
                System.out.println("El perro " + Thread.currentThread().getName() + " termino de comer");
                contPerrosComiendo--;
                if (contPerrosComiendo == 0) {
                    comedores.release(cantComedores);
                }
            }
            mutexEspecie.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comerGato(String unTipo) {
        try {
            comedores.acquire();
            mutexEspecie.acquire();
            if (unTipo.equals("G")) {
                if (contGatosEsperando != 0) {
                    System.out.println("El gato " + Thread.currentThread().getName() + " esta comiendo");
                    contGatosEsperando--;
                    contGatosComiendo++;
                    contGatosComieron++;
                    if (contGatosComieron == max && contPerrosEsperando != 0) {
                        //cambio de prioridad
        
                        tipo = "P";
                        //liberamos los comedores
                    }
                } else {
                    if (contPerrosEsperando != 0) {
                        //cambio de prioridad
                        tipo = "P";
                        //liberamos los comedores
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
            mutexEspecie.acquire();
            if (contGatosComiendo != 0) {
                System.out.println("El gato " + Thread.currentThread().getName() + " termino de comer");
                contGatosComiendo--;
                if (contGatosComiendo == 0) {
                    System.out.println("termino");
                    comedores.release(cantComedores);
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
