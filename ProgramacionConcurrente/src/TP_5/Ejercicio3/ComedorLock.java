package TP_5.Ejercicio3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ComedorLock {
    private Lock accesoComedor;
    private Lock comer;
    private Lock comerPerro;
    private Lock comerGato;
    private Condition perros;
    private Condition gatos;
    private int perrosComiendo = 0;
    private int perrosEsperando = 0;
    private int perrosComieron = 0;
    private int gatosComiendo = 0;
    private int gatosComieron = 0;
    private int gatosEsperando = 0;
    private int max = 7;
    private int cantComerdores = 4;
    private String turno = "";

    public ComedorLock() {
        this.accesoComedor = new ReentrantLock();
        this.comerPerro = new ReentrantLock();
        this.comerGato = new ReentrantLock();
        this.comer = new ReentrantLock();
        this.perros = comer.newCondition();
        this.gatos = comer.newCondition();
    }

    public void ingresarComedor(String tipo) {
        try {
            accesoComedor.lock();
            if (perrosEsperando == 0 && perrosComiendo == 0 && gatosComiendo == 0 && gatosEsperando == 0) {
                if (tipo.equals("P")) {
                    this.turno = "P";
                } else {
                    this.turno = "G";
                }
            }
            if (tipo.equals("P")) {
                perrosEsperando++;
            } else {
                gatosEsperando++;
            }
        } finally {
            accesoComedor.unlock();
        }
    }

    public void comerPerro() {
        try {
            comer.lock();
            while (gatosComieron >= max || perrosComiendo >= cantComerdores || turno.equals("G")) {
                perros.await();
            }
            System.out.println("El perro " + Thread.currentThread().getName() + " esta comiendo");
            perrosEsperando--;
            perrosComiendo++;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            comer.unlock();
        }
    }

    public void finalizarComerPerro() {
        try {
            comer.lock();
            System.out.println("El perro " + Thread.currentThread().getName() + " termino de comer");
            perrosComiendo--;
            perrosComieron++;
            accesoComedor.lock();
            if (perrosComiendo == 0) {
                if (perrosComieron == max && gatosEsperando != 0) {
                    perrosComieron = 0;
                    this.turno = "G";
                    gatos.signalAll();
                } else {
                    if (perrosEsperando == 0 && gatosEsperando != 0) {
                        this.turno = "G";
                        gatos.signalAll();
                    } else {
                        if (perrosEsperando != 0 ) {
                            perros.signalAll();
                        } else {
                            this.turno = "G";
                            gatos.signalAll();
                        }
                    }
                }
            }

            accesoComedor.unlock();
        } finally {
            comer.unlock();
        }
    }

    public void comerGato() {
        try {
            comer.lock();
            while (gatosComieron >= max || gatosComiendo >= cantComerdores || turno.equals("P")) {
                gatos.await();
            }
            System.out.println("El gato " + Thread.currentThread().getName() + " esta comiendo");
            gatosEsperando--;
            gatosComiendo++;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            comer.unlock();
        }
    }

    public void finalizarComerGato() {
        try {

            comer.lock();
            System.out.println("El gato " + Thread.currentThread().getName() + " termino de comer");
            gatosComiendo--;
            gatosComieron++;
            accesoComedor.lock();
            if (gatosComiendo == 0) {
                if (gatosComieron == max && perrosEsperando != 0) {
                    gatosComieron = 0;
                    this.turno = "P";
                    perros.signalAll();
                } else {
                    if (gatosEsperando == 0 && perrosEsperando != 0) {
                        this.turno = "P";
                        perros.signalAll();
                    } else {
                        if (gatosEsperando != 0) {
                            gatos.signalAll();
                        } else {
                            this.turno = "P";
                            perros.signalAll();
                        }
                    }
                }
            }
            accesoComedor.unlock();
        } finally {
            comer.unlock();
        }
    }

}
