package test;

import java.util.ArrayList;
import java.util.List;


public class Test {

	public static void main(String[] args) {
		Persistence p = new Persistence();
		List<Persona> personas = new ArrayList<Persona>();
		p.cargarPersonasDesdeArchivo_json(personas);
		
		for (Persona persona : personas) {
			System.out.println(persona.toString());
		}
	}
}
