/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_3.Ejercicio5;

/**
 *
 * @author galin
 */
public class Auto {

    private String matricula;
    private String modelo;
    private int km;
    private String marca;
    private int litrosCombustible;
    private Surtidor surtidor;

    public Auto(String matricula, String modelo, int km, String marca) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.km = km;
        this.marca = marca;
    }

    public int getLitrosCombustible() {
        return litrosCombustible;
    }

    public void setLitrosCombustible(int litrosCombustible) {
        this.litrosCombustible = litrosCombustible;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void recorrerKilometros(int km) {
        this.km = this.km + km;

    }

    //cada 10 km comsume 1 litro de combustible
    public void consumoCombustible(int km) {
        if (this.litrosCombustible > (km / 10)) {
            this.litrosCombustible = this.litrosCombustible - km / 10;
        } else {
            //reserva
            System.out.println("Reserva");
            System.out.println("");
            System.out.println("Debe cargar Combustible");
        }

    }

}
