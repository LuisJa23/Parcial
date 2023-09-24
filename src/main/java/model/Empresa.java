package model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
	
    private String nombre_empresa;
    private String codigo_empresa;
    private List<Sede> lista_sedes_empresa;

    public Empresa() {
    }

    public Empresa(String nombre_empresa, String codigo_empresa) {
        this.nombre_empresa = nombre_empresa;
        this.codigo_empresa = codigo_empresa;
        this.lista_sedes_empresa = new ArrayList<>(); // Inicialización de la lista
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getCodigo_empresa() {
        return codigo_empresa;
    }

    public void setCodigo_empresa(String codigo_empresa) {
        this.codigo_empresa = codigo_empresa;
    }

    public List<Sede> getLista_sedes_empresa() {
        return lista_sedes_empresa;
    }

    public void setLista_sedes_empresa(List<Sede> lista_sedes_empresa) {
        this.lista_sedes_empresa = lista_sedes_empresa;
    }
    
    ///
    public void Lista_sedes_empresa(Sede sedes) {
        lista_sedes_empresa.add(sedes);
    }

    @Override
    public String toString() {
        return "Nombre Empresa: " + nombre_empresa
                + ", Código Empresa: " + codigo_empresa;
    }

}
