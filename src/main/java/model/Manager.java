package model;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    
	private List<Empresa> empresas;
	private List<Sede> sedes;
	private List<Empleado> empleados;
	private List<Persona> personas;
	
	public Manager() {
		this.empresas = new ArrayList<>();
		this.sedes = new ArrayList<>();
		this.empleados = new ArrayList<>();	
		this.personas = new ArrayList<>();
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public List<Sede> getSedes() {
		return sedes;
	}

	public void setSedes(List<Sede> sedes) {
		this.sedes = sedes;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	//---------------------------- Empresas --------------------

	public void verEmpresasRegistradas() {
        if (empresas.isEmpty()) {                                        //Verifica si la lista está vacía
            System.out.println("No hay empresas registradas");
        } else {
            System.out.println("=== Empresas Registrados ===");
            int index = 0;
            for (Empresa empresa : empresas) {                     //bucle for-each que recorre la lista 
                System.out.println("Índice " + index + ": " + empresa);
                index++;
            }
        }
	}

	public void registrarEmpresa(String nameCompany, String codCompany) {                           // método para registrar empresa
		empresas.add(new Empresa(nameCompany,codCompany));  // agrega un nuevo objeto a la lista
	
	}

	public void eliminarEmpresa(int indice) { // método para eliminar empresas registrados de la lista empresas
		empresas.remove(indice);
	}
	
	public int sizeListEmpresas() {
		return empresas.size();
	}
	
	
	//---------------------------- Sedes--------------------
	
	public void verSedesRegistradas() { 
        if (sedes.isEmpty()) {
            System.out.println("No hay sedes registradas.");
        } else {
            System.out.println("=== sedes Registradas ===");
            int index = 0;
            for (Sede sede : sedes) {                     //bucle for-each que recorre la lista 
                System.out.println("Índice " + index + ": " + sede);
                index++;
            }
        }
	}

	public void registrarSede(String nombre_sede, String codigo_sede, String sede_principal) {                           // método para registrar empresa
		// agrega un nuevo objeto a la lista
		sedes.add(new Sede(nombre_sede,codigo_sede,sede_principal));
	
	}

	public void eliminarSede(int indice) { // método para eliminar empresas registrados de la lista empresas
		sedes.remove(indice);
	}
	
	public int sizeListSede() {
		return sedes.size();
	}
	
	//---------------------------- Empleados--------------------
	
	public void verEmpleadosRegistrados() {
		System.out.println("=== Empleados Registrados ===");

		// Verificar si hay empleados registrados
		if (empleados.isEmpty()) {
			System.out.println("No hay empleados registrados.");
			return;
		}

		// Recorrer la lista de empleados y mostrar sus detalles
		for (int i = 0; i < empleados.size(); i++) {
			Empleado empleado = empleados.get(i);
			System.out.println("Empleado :");
			System.out.println("Cargo: " + empleado.getCargo());

			List<Persona> personasAsociadas = empleado.getLista_personas_cargo();
			System.out.println("Personas asociadas al cargo:");
			for (int j = 0; j < personasAsociadas.size(); j++) {
				Persona persona = personasAsociadas.get(j);
				System.out.println("  " + (j + 1) + ". ID Persona: " + persona.getId_persona());
				System.out.println("     Nombre y Apellido: " + persona.getNombre_apellido_persona());
				System.out.println("     Empleado Jefe (S/N): " + persona.getEmpleado_jefe());
			}
		}
		System.out.println();
	}

	public void registrarEmpleados(Cargo cargo, List<Persona> lista_personas_cargo ) {     // método para registrar empresa
		// agrega un nuevo objeto a la lista
		empleados.add(new Empleado(cargo,lista_personas_cargo));
	
	}

	public void modificarEmpleado(int indice, String new_id_person, String new_names,Cargo new_cargo) { // método para modificar empresas registrados en la lista

		Empleado empleadoSeleccionado = empleados.get(indice); // obtiene la empresa de la lista empresas que
																	// corresponde al índice especificado y lo almacena
																	// en la variable empresasSeleccionado

		empleadoSeleccionado.setCargo(new_cargo);
	}

	public void eliminarEmpleado(int indice) { // método para eliminar empresas registrados de la lista empresas
		empleados.remove(indice);
	}
	
	public int sizeListEmpleados() {
		return empleados.size();
	}
	
	//.---------------------------- personas--------------
	
	public void registrarPersona(String id_persona, String nombre_apellido_persona, String empleado_jefe ) {     // método para registrar empresa
		// agrega un nuevo objeto a la lista
		personas.add(new Persona(id_persona,nombre_apellido_persona,empleado_jefe));
	
	}
	
	public void verPersonasRegistradas() {
	    System.out.println("=== Personas Registradas ===");

	    // Verificar si hay personas registradas
	    if (personas.isEmpty()) {
	        System.out.println("No hay personas registradas.");
	    } else {
	        int index = 0;
	        for (Persona persona : personas) {
	            System.out.println("Índice " + index + ": " + persona);
	            index++;
	        }
	    }
	}
}
