package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class View {
	
	private static Scanner scanner = new Scanner(System.in); // Creación de un objeto Scanner para entrada de usuario

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public void mostrarMenuPrincipal() { // Método para mostrar el Menu_valida_opcion
		System.out.println("=== MENÚ - PRINCIPAL SISTEMA ERP ===");
		
		System.out.println("1. Gestion Empresa");
		System.out.println("2. Gestion Sede");
		System.out.println("3. Gestion Empleado");
		System.out.println("4. Gestion Persona");
		
        System.out.println("5. Asociar Sede a Empresa");
        System.out.println("6. Desasociar Sede Empresa");
        System.out.println("7. Ver Sedes de una empresa");
        System.out.println("8. Elegir Sede principal de una Empresa");
        System.out.println("9. Modificar Sede principal de Empresa");
        
		System.out.println("0. Salir");
	}
	
	public void mostrarMenuEmpresa() { // Método para mostrar el Menu_valida_opcion
		System.out.println("=== MENÚ - EMPRESA ===");
		System.out.println("1. Ver empresas registradas");
		System.out.println("2. Registrar una nueva empresa");
		System.out.println("3. Modificar registro de empresa");
		System.out.println("4. Eliminar registro de empresa");
		System.out.println("0. Salir");
	}
	
	public void mostrarMenuSedes() { // Método para mostrar el Menu_valida_opcion
		System.out.println("=== MENÚ - SEDES ===");
		System.out.println("1. Ver sedes registradas");
		System.out.println("2. Registrar una nueva sede");
		System.out.println("3. Modificar registro de sede");
		System.out.println("4. Eliminar registro de sede");
		System.out.println("0. Salir");
	}
	
	public void mostrarMenuEmpleados() { // Método para mostrar el Menu_valida_opcion
		System.out.println("=== MENÚ - EMPLEADOS ===");
		System.out.println("1. Ver empleados");
		System.out.println("2. Registrar un nuevo empleado");
		System.out.println("0. Salir");
	}
	
	public void mostrarMenuPersonas() { // Método para mostrar el Menu_valida_opcion
		System.out.println("=== MENÚ - PERSONAS ===");
		System.out.println("1. Ver personas");
		System.out.println("2. Registrar una nueva persona");
		System.out.println("3. Modificar registro de persona");
		System.out.println("4. Eliminar registro de persona");
		System.out.println("0. Salir");
	}
	
	public void mostrarMenuEmpresaSede() { // Método para mostrar el Menu_valida_opcion
		System.out.println("=== MENÚ - EMPRESA - SEDE ===");
		System.out.println("1. Asociar sede a empresa");
		System.out.println("2. Sedes por empresa");;
		System.out.println("3. Eliminar sede de empresa");
		System.out.println("0. Salir");
	}
	
	public void mostrarMenuSedeEmpleado() { // Método para mostrar el Menu_valida_opcion
		System.out.println("=== MENÚ - SEDE - EMPLEADO  ===");
		System.out.println("1. Asociar empleado a sede");
		System.out.println("2. Ver empleados por sede");;
		System.out.println("3. Eliminar empleado de sede");
		System.out.println("0. Salir");
	}

	public int leerOpcion() { // Método para leer la opción ingresada por el usuario
		int opcion;
        while (true) {
            try {
                System.out.print("Ingrese una opción: ");
                String input = reader.readLine().trim();
                if (!input.isEmpty()) {
                    opcion = Integer.parseInt(input);
                    if (opcion >= 0 && opcion <= 8) {
                        break;
                    } else {
                        System.out.println("Opción no válida. Intente nuevamente.");
                    }
                } else {
                    System.out.println("No se permiten campos vacíos. Intente nuevamente.");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
        return opcion;
	}

	public void closeScanner() {
		scanner.close(); // Cerrar el objeto Scanner al finalizar
	}

	// ------------------------------------------------------------------------------

	public void showText(String text) {
		System.out.println(text);
	}
    
	public int leerIndiceValido(int maximo) {
		int indice;
        while (true) {
            try {
                System.out.print("Ingrese un índice válido: ");
                String input = reader.readLine().trim();
                indice = Integer.parseInt(input);
                if (indice >= 0 && indice < maximo) {
                    break;
                }
                System.out.println("Índice no válido. Intente nuevamente.");
            } catch (NumberFormatException | IOException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
        return indice;
	}

	public String leerCadenaNoVacia() { // método para validar entrada no vacía
		String input;
        while (true) {
            try {
                input = reader.readLine().trim();
                if (!input.isEmpty()) {
                    return input;
                }
                System.out.println("No se permiten campos vacíos. Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
	}

	public String leerCadenaNoVaciaTexto() { // método para validar entrada tipo texto
		String input;
        while (true) {
            try {
                input = reader.readLine().trim();
                if (!input.isEmpty() && input.matches("^[a-zA-Z\\s]+$")) {
                    return input;
                }
                System.out.println("Ingrese un valor válido (solo texto). Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
	}
	
    public String leerCadenaNoVaciaTextoPunto() {   //método leerCadenaNoVaciaTextoPunto()para validar entrada tipo texto con punto
    	String input;
        while (true) {
            try {
                input = reader.readLine().trim();
                if (!input.isEmpty() && input.matches("^[a-zA-Z.\\s]+$")) {
                    return input;
                }
                System.out.println("Ingrese un valor válido (texto y puntos). Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
    }

    public String leerCodigoNumerico() {
        String input;
        while (true) {
            try {
                input = reader.readLine().trim();
                if (!input.isEmpty() && input.matches("^[0-9]+$")) { // Verifica que la entrada contenga solo números
                    return input;
                }
                System.out.println("Ingrese un valor válido (solo números). Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
    }
    
    public String leerCorreoValido() {
        String input;
        while (true) {
            try {
                input = reader.readLine().trim();
                if (!input.isEmpty() && input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
                    return input;
                }
                System.out.println("Ingrese un correo electrónico válido. Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
    }
    
    

}
