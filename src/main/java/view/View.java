package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class View {
	
	private static Scanner scanner = new Scanner(System.in); // Creaci�n de un objeto Scanner para entrada de usuario

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public void mostrarMenuPrincipal() { // M�todo para mostrar el Menu_valida_opcion
		System.out.println("=== MEN� - PRINCIPAL SISTEMA ERP ===");
		
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
	
	public void mostrarMenuEmpresa() { // M�todo para mostrar el Menu_valida_opcion
		System.out.println("=== MEN� - EMPRESA ===");
		System.out.println("1. Ver empresas registradas");
		System.out.println("2. Registrar una nueva empresa");
		System.out.println("3. Modificar registro de empresa");
		System.out.println("4. Eliminar registro de empresa");
		System.out.println("0. Salir");
	}
	
	public void mostrarMenuSedes() { // M�todo para mostrar el Menu_valida_opcion
		System.out.println("=== MEN� - SEDES ===");
		System.out.println("1. Ver sedes registradas");
		System.out.println("2. Registrar una nueva sede");
		System.out.println("3. Modificar registro de sede");
		System.out.println("4. Eliminar registro de sede");
		System.out.println("0. Salir");
	}
	
	public void mostrarMenuEmpleados() { // M�todo para mostrar el Menu_valida_opcion
		System.out.println("=== MEN� - EMPLEADOS ===");
		System.out.println("1. Ver empleados");
		System.out.println("2. Registrar un nuevo empleado");
		System.out.println("0. Salir");
	}
	
	public void mostrarMenuPersonas() { // M�todo para mostrar el Menu_valida_opcion
		System.out.println("=== MEN� - PERSONAS ===");
		System.out.println("1. Ver personas");
		System.out.println("2. Registrar una nueva persona");
		System.out.println("3. Modificar registro de persona");
		System.out.println("4. Eliminar registro de persona");
		System.out.println("0. Salir");
	}
	
	public void mostrarMenuEmpresaSede() { // M�todo para mostrar el Menu_valida_opcion
		System.out.println("=== MEN� - EMPRESA - SEDE ===");
		System.out.println("1. Asociar sede a empresa");
		System.out.println("2. Sedes por empresa");;
		System.out.println("3. Eliminar sede de empresa");
		System.out.println("0. Salir");
	}
	
	public void mostrarMenuSedeEmpleado() { // M�todo para mostrar el Menu_valida_opcion
		System.out.println("=== MEN� - SEDE - EMPLEADO  ===");
		System.out.println("1. Asociar empleado a sede");
		System.out.println("2. Ver empleados por sede");;
		System.out.println("3. Eliminar empleado de sede");
		System.out.println("0. Salir");
	}

	public int leerOpcion() { // M�todo para leer la opci�n ingresada por el usuario
		int opcion;
        while (true) {
            try {
                System.out.print("Ingrese una opci�n: ");
                String input = reader.readLine().trim();
                if (!input.isEmpty()) {
                    opcion = Integer.parseInt(input);
                    if (opcion >= 0 && opcion <= 8) {
                        break;
                    } else {
                        System.out.println("Opci�n no v�lida. Intente nuevamente.");
                    }
                } else {
                    System.out.println("No se permiten campos vac�os. Intente nuevamente.");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Error: Ingrese un n�mero v�lido.");
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
                System.out.print("Ingrese un �ndice v�lido: ");
                String input = reader.readLine().trim();
                indice = Integer.parseInt(input);
                if (indice >= 0 && indice < maximo) {
                    break;
                }
                System.out.println("�ndice no v�lido. Intente nuevamente.");
            } catch (NumberFormatException | IOException e) {
                System.out.println("Error: Ingrese un n�mero v�lido.");
            }
        }
        return indice;
	}

	public String leerCadenaNoVacia() { // m�todo para validar entrada no vac�a
		String input;
        while (true) {
            try {
                input = reader.readLine().trim();
                if (!input.isEmpty()) {
                    return input;
                }
                System.out.println("No se permiten campos vac�os. Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
	}

	public String leerCadenaNoVaciaTexto() { // m�todo para validar entrada tipo texto
		String input;
        while (true) {
            try {
                input = reader.readLine().trim();
                if (!input.isEmpty() && input.matches("^[a-zA-Z\\s]+$")) {
                    return input;
                }
                System.out.println("Ingrese un valor v�lido (solo texto). Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
	}
	
    public String leerCadenaNoVaciaTextoPunto() {   //m�todo leerCadenaNoVaciaTextoPunto()para validar entrada tipo texto con punto
    	String input;
        while (true) {
            try {
                input = reader.readLine().trim();
                if (!input.isEmpty() && input.matches("^[a-zA-Z.\\s]+$")) {
                    return input;
                }
                System.out.println("Ingrese un valor v�lido (texto y puntos). Intente nuevamente.");
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
                if (!input.isEmpty() && input.matches("^[0-9]+$")) { // Verifica que la entrada contenga solo n�meros
                    return input;
                }
                System.out.println("Ingrese un valor v�lido (solo n�meros). Intente nuevamente.");
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
                System.out.println("Ingrese un correo electr�nico v�lido. Intente nuevamente.");
            } catch (IOException e) {
                System.out.println("Error al leer la entrada.");
            }
        }
    }
    
    

}
