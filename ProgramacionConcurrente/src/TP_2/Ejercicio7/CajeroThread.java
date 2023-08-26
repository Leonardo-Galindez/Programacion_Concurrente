/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_2.Ejercicio7;

/**
 *
 * @author galin
 */
public class CajeroThread extends Thread {

    private String nombre;
    private Cliente cliente;
    private long initialTime;
    // Constructor, y métodos de acceso

    public CajeroThread(String nombre, Cliente cliente, long initialTime) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.initialTime = initialTime;
    }

    public void run() {
        System.out.println("El cajero " + this.nombre
                + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE "
                + this.cliente.getNombre() + " EN EL TIEMPO: "
                + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1) + "del cliente " + this.cliente.getNombre() + "->Tiempo: " + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
        }
        System.out.println("El cajero" + this.nombre + "HA TERMINADO DE PROCESAR" + this.cliente.getNombre() + " EN EL TIEMPO: " + (System.currentTimeMillis() - this.initialTime) / 1000 + "seg");
    }

    public void procesarCompra(Cliente cliente, long timeStamp) {
        System.out.println("El cajero " + this.nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + cliente.getNombre() + "EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
        for (int i = 0; i < cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]);//espera para cada producto
            System.out.println("Procesado el producto " + (i + 1) + "->Tiempo:" + (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
        }
        System.out.println("El cajero " + this.nombre + "HA TERMINADO DE PROCESAR " + cliente.getNombre() + " EN EL TIEMPO: " + (System.currentTimeMillis() - timeStamp) / 1000 + "seg");
    }

    private void esperarXsegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000); // Convierte segundos a milisegundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("El producto cajero fue interrumpido mientras dormia");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(long initialTime) {
        this.initialTime = initialTime;
    }

}
