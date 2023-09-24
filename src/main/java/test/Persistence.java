package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;// Importación necesaria para manejar tipos genéricos al deserializar JSON con Jackson.
import com.fasterxml.jackson.databind.ObjectMapper; // Importación necesaria para utilizar ObjectMapper de Jackson para la deserialización y serialización de JSON.


public class Persistence {
	

	 /**
     * Este método se utiliza para guardar la lista de empresas en un archivo
     * JSON. Utiliza la biblioteca Jackson ObjectMapper para serializar la lista
     * de empresas en formato JSON y escribirlo en el archivo "empresas.json".
     * Si el archivo no existe, se crea. El JSON resultante se formatea con
     * saltos de línea y sangría para una mejor legibilidad.
     */
	

    
    public void guardarPersonasEnArchivo_json(List<Persona> personas) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("personas.json");

            // Utiliza writerWithDefaultPrettyPrinter() para formatear el JSON con saltos de línea y sangría
            // Luego, se escribe la lista de sedes en el archivo JSON.
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), personas);
            System.out.println("Datos de persona guardados en el archivo JSON.");  // Se muestra un mensaje de éxito en la consola.

        } catch (IOException e) {  // En caso de error, se muestra un mensaje de error en la consola.
            System.out.println("Error al guardar los datos de persona en el archivo JSON.");
        }
    }

    public void cargarPersonasDesdeArchivo_json(List<Persona> personas) {
        personas.clear(); // Limpiamos la lista actual antes de cargar los datos.

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("personas.json");

            if (Files.exists(filePath)) {
                List<Persona> personasCargadas = objectMapper.readValue(filePath.toFile(), new TypeReference<List<Persona>>() {
                });

                // Iteramos sobre las personas cargadas y añadimos cada uno a la lista de personas actual.
                for (Persona persona : personasCargadas) {
                    // Verificamos si la persona ya existe en la lista actual.
                    boolean existe = false;
                    for (Persona d : personas) {
                        if (d.getId_persona().equals(persona.getId_persona())) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        personas.add(persona);
                    }
                }

                System.out.println("Datos de persona cargados desde el archivo JSON.");
            } else {
                System.out.println("El archivo 'personas.json' no existe.");
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar los datos de personas desde el archivo JSON.");
        }
    }

}
