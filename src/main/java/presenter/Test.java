package presenter;

import java.util.ArrayList;
import java.util.List;

import model.Persona;

public class Test {

	public static void main(String[] args) {
		Persistence p = new Persistence();
		List<Persona> personas = new ArrayList<>();
		p.cargarPersonasDesdeArchivo_json(personas);
		
		for (Persona persona : personas) {
			persona.toString();
		}
	}
}
