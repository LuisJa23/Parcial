package model;

import java.util.ArrayList;
import java.util.List;

public class Sede {
	
    private String nombre_sede;
    private String codigo_sede;
    private String sede_principal;
    private List<Empleado> lista_empleados_sede;

    //constructor vac�o
    public Sede() {
    }

    // Constructor con par�metros
    public Sede(String nombre_sede, String codigo_sede, String sede_principal) {
        this.nombre_sede = nombre_sede;
        this.codigo_sede = codigo_sede;
        this.sede_principal = sede_principal;

        this.lista_empleados_sede = new ArrayList<>(); // Inicializaci�n de la lista
    }

    public String getNombre_sede() {
        return nombre_sede;
    }

    public void setNombre_sede(String nombre_sede) {
        this.nombre_sede = nombre_sede;
    }

    public String getCodigo_sede() {
        return codigo_sede;
    }

    public void setCodigo_sede(String codigo_sede) {
        this.codigo_sede = codigo_sede;
    }

    public String getSede_principal() {
        return sede_principal;
    }

    public void setSede_principal(String sede_principal) {
        this.sede_principal = sede_principal;
    }

    public List<Empleado> getLista_empleados_sede() {
        return lista_empleados_sede;
    }

    public void setLista_empleados_sede(List<Empleado> lista_empleados_sede) {
        this.lista_empleados_sede = lista_empleados_sede;
    }

//Getters y setters 
    public void Lista_empleados_sede(Empleado empleados) {
        lista_empleados_sede.add(empleados);
    }

    @Override
    public String toString() {
        return "Nombre Sede: " + nombre_sede
                + ", C�digo Sede: " + codigo_sede
                + ", Sede Principal(S/N): " + sede_principal;
    }

}
