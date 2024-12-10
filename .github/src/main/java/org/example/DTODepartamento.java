package org.example;

import java.util.ArrayList;

public class DTODepartamento {
    private int id;
    private String nombre;
    private ArrayList<DTOEmpleado> empleados;

    public DTODepartamento() {
        super();
    }

    public DTODepartamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<DTOEmpleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<DTOEmpleado> empleados) {
        this.empleados = empleados;
    }

    @Override
    public String toString() {
        return "DTODepartamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", empleados=" + empleados +
                '}';
    }
}
