package model;

import java.util.List;

public class Empleado {

	private Cargo cargo;
	private List<Persona> lista_personas_cargo;

	public Empleado() {
	}

	public Empleado(Cargo cargo, List<Persona> lista_personas_cargo) {
		this.cargo = cargo;
		this.lista_personas_cargo = lista_personas_cargo;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public List<Persona> getLista_personas_cargo() {
		return lista_personas_cargo;
	}

	public void setLista_personas_cargo(List<Persona> lista_personas_cargo) {
		this.lista_personas_cargo = lista_personas_cargo;
	}

	public void Lista_personas_cargo(Persona persona) {
		lista_personas_cargo.add(persona);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cargo: ").append(cargo);

		builder.append("Personas asociadas al cargo: \n");
		for (Persona persona : lista_personas_cargo) {
			builder.append(persona.toString()).append(", ");
		}
		return builder.toString();
	}

}
