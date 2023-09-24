package presenter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import com.fasterxml.jackson.core.type.TypeReference;// Importación necesaria para manejar tipos genéricos al deserializar JSON con Jackson.
import com.fasterxml.jackson.databind.ObjectMapper; // Importación necesaria para utilizar ObjectMapper de Jackson para la deserialización y serialización de JSON.

import javax.xml.parsers.DocumentBuilderFactory; // Importa la clase DocumentBuilderFactory, que se utiliza para crear instancias de DocumentBuilder para analizar documentos XML.
import javax.xml.parsers.DocumentBuilder; // Importa la clase DocumentBuilder, que se utiliza para analizar documentos XML.
import org.w3c.dom.Document; // Importa la clase Document, que representa un documento XML en el DOM.
import org.w3c.dom.Element; // Importa la clase Element, que representa un elemento XML en el DOM.
import org.w3c.dom.NodeList; // Importa la clase NodeList, que se utiliza para trabajar con listas de nodos en el DOM XML.
//Document Object Model (Modelo de Objetos de Documento)

import model.Empleado;
import model.Empresa;
import model.Persona;
import model.Sede;

public class Persistence {
	/**
     * Carga y muestra el menú de opciones desde un archivo XML. El método lee
     * el archivo "menu.xml", analiza su contenido y muestra las opciones en la
     * consola. Si se produce un error durante el proceso de lectura o análisis,
     * muestra un mensaje de error.
     */
    public void cargarMenuDesdeXML() {
        try {
// Crear un objeto File que representa el archivo "menu.xml"
            File menuFile = new File("menu.xml");
// Crear una instancia de DocumentBuilderFactory para configurar el analizador XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
// Crear un objeto DocumentBuilder para analizar el contenido XML
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
// Analizar el contenido del archivo XML y crear un documento DOM: Document Object Model (Modelo de Objetos de Documento)
            Document doc = dBuilder.parse(menuFile);
// Obtener la lista de nodos "option" del documento XML
            NodeList options = doc.getElementsByTagName("option");
// Mostrar el menú en la consola
            System.out.println("=== MENÚ - PRINCIPAL EN/ES ===");
            for (int i = 0; i < options.getLength(); i++) {
                Element option = (Element) options.item(i);
                System.out.println(option.getTextContent());
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el menú desde el archivo XML.");
        }
    }
    
    public void cargarMenuEmpresaXML() {
        try {
            File menuFile = new File("menu empresa.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();Document doc = dBuilder.parse(menuFile);
            NodeList options = doc.getElementsByTagName("option");
            System.out.println("=== MENÚ - GESTION DE EMPRESA EN/ES ===");
            for (int i = 0; i < options.getLength(); i++) {
                Element option = (Element) options.item(i);
                System.out.println(option.getTextContent());
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el menú desde el archivo XML.");
        }
    }
    
    public void cargarMenuSedeXML() {
        try {
            File menuFile = new File("menu sede.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();Document doc = dBuilder.parse(menuFile);
            NodeList options = doc.getElementsByTagName("option");
            System.out.println("=== MENÚ - GESTION DE SEDES EN/ES ===");
            for (int i = 0; i < options.getLength(); i++) {
                Element option = (Element) options.item(i);
                System.out.println(option.getTextContent());
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el menú desde el archivo XML.");
        }
    }

	 /**
     * Este método se utiliza para guardar la lista de empresas en un archivo
     * JSON. Utiliza la biblioteca Jackson ObjectMapper para serializar la lista
     * de empresas en formato JSON y escribirlo en el archivo "empresas.json".
     * Si el archivo no existe, se crea. El JSON resultante se formatea con
     * saltos de línea y sangría para una mejor legibilidad.
     */
	

    public void guardarEmpresasEnArchivo_json(List<Empresa> empresas) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("empresas.json");

            // Utiliza writerWithDefaultPrettyPrinter() para formatear el JSON con saltos de línea y sangría
            // Luego, se escribe la lista de departamentos en el archivo JSON.
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), empresas);
            System.out.println("Datos de Empresa guardados en el archivo JSON.");  // Se muestra un mensaje de éxito en la consola.

        } catch (IOException e) {  // En caso de error, se muestra un mensaje de error en la consola.
            System.out.println("Error al guardar los datos de Empresa en el archivo JSON.");
        }
    }

    public void cargarEmpresasDesdeArchivo_json(List<Empresa> empresas) {
        empresas.clear(); // Limpiamos la lista actual antes de cargar los datos.

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("empresas.json");

            if (Files.exists(filePath)) {
                List<Empresa> empresasCargadas = objectMapper.readValue(filePath.toFile(), new TypeReference<List<Empresa>>() {
                });

                // Iteramos sobre los departamentos cargados y añadimos cada uno a la lista de departamentos actual.
                for (Empresa empresa : empresasCargadas) {
                    // Verificamos si el departamento ya existe en la lista actual.
                    boolean existe = false;
                    for (Empresa d : empresas) {
                        if (d.getCodigo_empresa().equals(empresa.getCodigo_empresa())) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        empresas.add(empresa);
                    }
                }

                System.out.println("Datos de Empresa cargados desde el archivo JSON.");
            } else {
                System.out.println("El archivo 'empresas.json' no existe.");
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar los datos de empresas desde el archivo JSON.");
        }
    }

    public void guardarSedesEnArchivo_json(List<Sede> sedes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("sedes.json");

            // Utiliza writerWithDefaultPrettyPrinter() para formatear el JSON con saltos de línea y sangría
            // Luego, se escribe la lista de sedes en el archivo JSON.
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), sedes);
            System.out.println("Datos de Sede guardados en el archivo JSON.");  // Se muestra un mensaje de éxito en la consola.

        } catch (IOException e) {  // En caso de error, se muestra un mensaje de error en la consola.
            System.out.println("Error al guardar los datos de Sede en el archivo JSON.");
        }
    }

    public void cargarSedesDesdeArchivo_json(List<Sede> sedes) {
        sedes.clear(); // Limpiamos la lista actual antes de cargar los datos.

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("sedes.json");

            if (Files.exists(filePath)) {
                List<Sede> sedesCargadas = objectMapper.readValue(filePath.toFile(), new TypeReference<List<Sede>>() {
                });

                // Iteramos sobre los departamentos cargados y añadimos cada uno a la lista de departamentos actual.
                for (Sede sede : sedesCargadas) {
                    // Verificamos si el departamento ya existe en la lista actual.
                    boolean existe = false;
                    for (Sede d : sedes) {
                        if (d.getCodigo_sede().equals(sede.getCodigo_sede())) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        sedes.add(sede);
                    }
                }

                System.out.println("Datos de Sede cargados desde el archivo JSON.");
            } else {
                System.out.println("El archivo 'sedes.json' no existe.");
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar los datos de sedes desde el archivo JSON.");
        }
    }

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
////////////
    public void guardarEmpleadosEnArchivo_json(List<Empleado> empleados) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("empleados.json");

            // Utiliza writerWithDefaultPrettyPrinter() para formatear el JSON con saltos de línea y sangría
            // Luego, se escribe la lista de sedes en el archivo JSON.
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), empleados);
            System.out.println("Datos de empleado guardados en el archivo JSON.");  // Se muestra un mensaje de éxito en la consola.

        } catch (IOException e) {  // En caso de error, se muestra un mensaje de error en la consola.
            System.out.println("Error al guardar los datos de empleado en el archivo JSON.");
        }
    }

    public void cargarEmpleadosDesdeArchivo_json(List<Empleado> empleados) {
        empleados.clear(); // Limpiamos la lista actual antes de cargar los datos.

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Path filePath = Paths.get("empleados.json");

            if (Files.exists(filePath)) {
                List<Empleado> empleadosCargados = objectMapper.readValue(filePath.toFile(), new TypeReference<List<Empleado>>() {
                });

                // Iteramos sobre las personas cargadas y añadimos cada uno a la lista de personas actual.
                for (Empleado empleado : empleadosCargados) {
                    // Verificamos si la persona ya existe en la lista actual.
                    boolean existe = false;
                    for (Empleado d : empleados) {
                        if (d.getCargo().equals(empleado.getCargo())) {
                            existe = true;
                            break;
                        }
                    }
                    if (!existe) {
                        empleados.add(empleado);
                    }
                }

                System.out.println("Datos de empleados cargados desde el archivo JSON.");
            } else {
                System.out.println("El archivo 'empleados.json' no existe.");
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar los datos de empleados desde el archivo JSON.");
        }
    }

}
