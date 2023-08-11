/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TP_0.Ejercicio3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author galin
 */
public class Empresa {

    //utilizamos un atributo de lista 
    private List<Empleado> colEmpleados;
    private static Object[][] adicionalesCategoria
            = {
                {500, "principal"},
                {500, "comun"},
                {500, "gerente"}
            };
    private static Object[][] adicionalesAsistencia
            = {
                {100, "10"},
                {500, "20"},
                {1500, "30"}
            };
    private static Object[][] adicionalesRol
            = {
                {1000, "frontEnd"},
                {1000, "backEnd"},
                {1000, "fullStack"}
            };

    public List<Empleado> getColEmpleados() {
        return colEmpleados;
    }

    public void setColEmpleados(List<Empleado> colEmpleados) {
        this.colEmpleados = colEmpleados;
    }

    public static Object[][] getAdicionalesCategoria() {
        return adicionalesCategoria;
    }

    public static Object[][] getAdicionalesAsistencia() {
        return adicionalesAsistencia;
    }

    public static Object[][] getAdicionalesRol() {
        return adicionalesRol;
    }

    public Empresa() {
        this.colEmpleados = new ArrayList<>();
    }

    public List<Empleado> getListaDinamica() {
        return colEmpleados;
    }

    public void setListaDinamica(List<Empleado> listaDinamica) {
        this.colEmpleados = listaDinamica;
    }

    //metodo que genera una coleccion de empleados con antiguedad mayor a 10 años
    public List<Empleado> generarColeccionAntiguedad() {
        List<Empleado> colAntiguedad = new ArrayList<>();
        //recorremos la lista 
        for (Empleado empleado : colEmpleados) {
            if (empleado.verificarAntiguedad()) {
                colAntiguedad.add(empleado);
            }
        }
        return colAntiguedad;
    }
}
