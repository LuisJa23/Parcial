package presenter;

import java.util.ArrayList;

import model.*;
import view.View;

public class Presenter {
	
	private View view;
	private Manager manager;
	private Persistence persistence;

	public Presenter() {
		this.view = new View();
		this.manager = new Manager();	
		this.persistence = new Persistence();
	}

	public void run() {
		// llamado a los metodos para persistir los archivos txt y json
		persistence.cargarEmpresasDesdeArchivo_json(manager.getEmpresas());
		persistence.cargarSedesDesdeArchivo_json(manager.getSedes());
		persistence.cargarPersonasDesdeArchivo_json(manager.getPersonas());
		persistence.cargarEmpleadosDesdeArchivo_json(manager.getEmpleados());

		// Agregar el hook de apagado para guardar datos antes de salir (gancho de apagado:
		//se ejecutará justo antes de que el programa finalice su ejecución, ya sea porque está terminando normalmente o debido a una finalización abrupta)
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {

			persistence.guardarEmpresasEnArchivo_json(manager.getEmpresas());
			persistence.guardarSedesEnArchivo_json(manager.getSedes());
			persistence.guardarPersonasEnArchivo_json(manager.getPersonas());
			persistence.guardarEmpleadosEnArchivo_json(manager.getEmpleados());

			System.out.println("Guardando datos antes de salir...");
		}));

		int opcion; // Variable para almacenar la opción seleccionada por el usuario

		do { // Inicio del bucle "do-while"
			
			//view.mostrarMenuPrincipal(); // Llamada al método que muestra el Menu_valida_opcion
			persistence.cargarMenuDesdeXML();
			opcion = view.leerOpcion(); // Llamada al método que lee la opción ingresada por el usuario

			switch (opcion) { // Estructura de control "switch" para manejar las diferentes opciones
			case 1:
				menuEmpresa();
				break;
				
			case 2:
				menuSede();
		        break;
				
			case 3:
				menuEmpleado();
				
				break;
			case 4:
				menuPersona();
				break;
				
			case 5:
				Asociar_Sede_Empresa();
				break;
	
            case 6:
            	desasociarSedeEmpresa();
                break;
            case 7:
            	ver__Sedes_Empresa();
            	break;
            case 8:
            	elegir_sede_principal_empresa();
            	break;
            case 9:
            	modificar_sede_principal_empresa();
            	break;
				
			case 0:
				view.showText("¡Hasta luego!");
				break;
			default:
				view.showText("Opción no válida. Intente nuevamente.");
			}
		} while (opcion != 0); // El bucle se repetirá mientras la opción no sea 0
		
		// Llamado a los métodos para guardar los datos antes de salir
        
        persistence.guardarEmpresasEnArchivo_json(manager.getEmpresas());
		persistence.guardarSedesEnArchivo_json(manager.getSedes());
		persistence.guardarPersonasEnArchivo_json(manager.getPersonas());
		persistence.guardarEmpleadosEnArchivo_json(manager.getEmpleados());

		view.closeScanner();
	}
	

	public void menuEmpresa() {

		int opcion; // Variable para almacenar la opción seleccionada por el usuario
		do {
			//view.mostrarMenuEmpresa(); // Llamada al método que muestra el Menu_Estudiante
			persistence.cargarMenuEmpresaXML();
			opcion = view.leerOpcion(); // Llamada al método que lee la opción ingresada por el usuario

			switch (opcion) {
			case 1:
				manager.verEmpresasRegistradas();
				break;

			case 2:
		        view.showText("=== Registrar Empresa ===");

		        // Declaración de variables para almacenar los datos del país
		        String nombre_empresa = null;
		        String codigo_empresa = null;
		        // Ciclo para validar y registrar los datos 
		        while (true) {
		            if (nombre_empresa == null) {
		                System.out.print("nombre empresa: ");
		                nombre_empresa = view.leerCadenaNoVaciaTexto().toUpperCase();

		                // Validar si el pais ya está registrado por nombre
		                for (Empresa empresa : manager.getEmpresas()) {
		                    if (empresa.getNombre_empresa().equalsIgnoreCase(nombre_empresa)) {
		                        System.out.println("la empresa con este nombre ya está registrada.");
		                        nombre_empresa = null; // Reiniciar para volver a pedir el dato
		                        break;
		                    }
		                }
		            } // Si el codigo del pais aún no se ha ingresado
		            else if (codigo_empresa == null) {
		                System.out.print("código empresa: ");
		                codigo_empresa = view.leerCodigoNumerico();

		                // Validar si el pais ya está registrado por código
		                for (Empresa empresa : manager.getEmpresas()) {
		                    if (empresa.getCodigo_empresa().equals(codigo_empresa)) {
		                        System.out.println("La empresa con este código ya está registrada");
		                        codigo_empresa = null; // Reiniciar para volver a pedir el dato
		                        break;
		                    }
		                }
		            }
		            // Si se han ingresado todos los datos requeridos, registrar el país
		            if (nombre_empresa != null && codigo_empresa != null) {
		            	manager.registrarEmpresa(nombre_empresa, codigo_empresa);
		                System.out.println("Empresa registrada exitosamente.");
		                persistence.guardarEmpresasEnArchivo_json(manager.getEmpresas());
		                break; // Salir del bucle en caso de éxito
		            }
		        }

				break;
				
			case 3:

		        view.showText("=== Modificar Registro de Empresa ===");

		        // Verificar si hay empresas registrados
		        if (manager.getEmpresas().isEmpty()) {
		        	view.showText("No hay empresas registrados.");
		            return;
		        }

		        // Mostrar la lista de empresas registrados
		        manager.verEmpresasRegistradas();

		        view.showText("Ingrese el índice de la empresa que desea modificar: ");
		        int indice = view.leerIndiceValido(manager.sizeListEmpresas());
		        Empresa empresaSeleccionada = manager.getEmpresas().get(indice);

		        // Inicializar variables con los valores actuales de la empresa seleccionada
		        String nuevoNombreEmpresa = empresaSeleccionada.getNombre_empresa();
		        String nuevoCodigoEmpresa = empresaSeleccionada.getCodigo_empresa();

		        // Solicitar al usuario que ingrese el nuevo nombre de empresa
		        while (true) {
		            view.showText("Nuevo nombre de empresa(" + nuevoNombreEmpresa + "): ");
		            String input = view.leerCadenaNoVaciaTexto().toUpperCase();
		            boolean nombreEmpresaRegistrada = false;
		            if (!input.isEmpty()) {
		                for (Empresa empresa : manager.getEmpresas()) {
		                    if (empresa.getNombre_empresa().equals(input) && !empresa.getNombre_empresa().equals(nuevoNombreEmpresa)) {
		                        view.showText("La empresa con este nombre ya está registrada");
		                        nombreEmpresaRegistrada = true;
		                        break;
		                    }
		                }
		                if (!nombreEmpresaRegistrada) {
		                    nuevoNombreEmpresa = input;
		                    break;
		                }
		            } else {
		            	view.showText("No se permiten campos vacíos. Intente nuevamente.");
		            }
		        }

		        // Solicitar al usuario que ingrese el nuevo código de empresa
		        while (true) {
		        	view.showText("Nuevo código de la empresa(" + nuevoCodigoEmpresa + "): ");
		            String input = view.leerCodigoNumerico();
		            boolean codigoEmpresaRegistrada = false;
		            if (!input.isEmpty()) {
		                for (Empresa empresa : manager.getEmpresas()) {
		                    if (empresa.getCodigo_empresa().equals(input) && !empresa.getCodigo_empresa().equals(nuevoCodigoEmpresa)) {
		                        System.out.println("La empresa con este código ya está registrada");
		                        codigoEmpresaRegistrada = true;
		                        break;
		                    }
		                }
		                if (!codigoEmpresaRegistrada) {
		                    nuevoCodigoEmpresa = input;
		                    break;
		                }
		            } else {
		            	view.showText("No se permiten campos vacíos. Intente nuevamente.");
		            }
		        }
		        // Actualizar los datos de la empresa con los valores nuevos
		        empresaSeleccionada.setNombre_empresa(nuevoNombreEmpresa);
		        empresaSeleccionada.setCodigo_empresa(nuevoCodigoEmpresa);
		        view.showText("Empresa modificado exitosamente.");
		        persistence.guardarEmpresasEnArchivo_json(manager.getEmpresas());

				break;

			case 4:

				view.showText("=== Eliminar Registro de Empresa ===");
		        if (manager.getEmpresas().isEmpty()) {
		        	view.showText("No hay empresas registrados.");
		            return;
		        }
		        manager.verEmpresasRegistradas();
		        view.showText("Ingrese el índice de la empresa que desea eliminar: ");
		        int indic = view.leerIndiceValido(manager.sizeListEmpresas());
		        manager.eliminarEmpleado(indic);
		        view.showText("empresa eliminada exitosamente.");
		        persistence.guardarEmpresasEnArchivo_json(manager.getEmpresas());
				break;

			case 0:
				view.showText("¡Hasta luego!");
				break;
			default:
				view.showText("Opción no válida. Intente nuevamente.");
			}
		} while (opcion !=0);
		
	}
	
	private void menuSede() {
		int opcion; // Variable para almacenar la opción seleccionada por el usuario
		do {
			//view.mostrarMenuSedes(); // Llamada al método que muestra el Menu_Estudiante
			persistence.cargarMenuSedeXML();
			opcion = view.leerOpcion(); // Llamada al método que lee la opción ingresada por el usuario

			switch (opcion) {
			case 1:
				manager.verSedesRegistradas();
				break;

			case 2:
		        view.showText("=== Registrar sede ===");

		        // Declaración de variables para almacenar los datos
		        String nombre_sede = null;
		        String codigo_sede = null;

		        // Ciclo para validar y registrar los datos
		        while (true) {
		            if (nombre_sede == null) {
		            	view.showText("nombre sede: ");
		                nombre_sede = view.leerCadenaNoVaciaTexto().toUpperCase();

		                // Validar si el  ya está registrado por nombre
		                for (Sede sede : manager.getSedes()) {
		                    if (sede.getNombre_sede().equalsIgnoreCase(nombre_sede)) {
		                    	view.showText("la sede con este nombre ya está registrado.");
		                        nombre_sede = null; // Reiniciar para volver a pedir el dato
		                        break;
		                    }
		                }
		            } // Si el codigo de la sede aún no se ha ingresado
		            else if (codigo_sede == null) {
		            	view.showText("codigo sede: ");
		                codigo_sede = view.leerCodigoNumerico();

		                // Validar si la sede ya está registrado por código
		                for (Sede sede : manager.getSedes()) {
		                    if (sede.getCodigo_sede().equals(codigo_sede)) {
		                    	view.showText("La sede con este código ya está registrada");
		                        codigo_sede = null; // Reiniciar para volver a pedir el dato
		                        break;
		                    }
		                }
		            }

		            // Si se han ingresado todos los datos requeridos, registrar la SEDE
		            if (nombre_sede != null && codigo_sede != null) {
		            	manager.registrarSede(nombre_sede, codigo_sede,"N");
		            	view.showText("sede registrada exitosamente.");
		                persistence.guardarSedesEnArchivo_json(manager.getSedes());
		                break; // Salir del bucle en caso de éxito
		            }
		        }

				break;
				
			case 3:

				view.showText("=== Modificar Registro de Sede ===");
				manager.verSedesRegistradas();

				if (manager.getSedes().isEmpty()) {
					view.showText("No hay sedes registradas.");
					return;
				}
				manager.verSedesRegistradas();

				view.showText("Ingrese el índice de la sede que desea modificar: ");
				int indice = view.leerIndiceValido(manager.sizeListSede());
				
				Sede sedeSeleccionada = manager.getSedes().get(indice);// obtiene la empresa de la 
																				//lista empresas que  corresponde al índice especificado y lo almacena 

				view.showText("Nuevo Codigo de la Sede: ");
				String newCod = view.leerCadenaNoVacia(); // valida entrada no vacía

				view.showText("Nuevo Nombre de la Sede:  ");
				String newName = view.leerCadenaNoVacia();
				
				sedeSeleccionada.setCodigo_sede(newCod);
				sedeSeleccionada.setNombre_sede(newName);

				view.showText("Sede modificada exitosamente.");

				break;

			case 4:

				view.showText("=== Eliminar Registro de sedes ===");
		        if (manager.getSedes().isEmpty()) {
		        	view.showText("No hay sedes registradas.");
		            return;
		        }
		        manager.verSedesRegistradas();
		        view.showText("Ingrese el índice de la sede que desea eliminar: ");
		        int indicee = view.leerIndiceValido(manager.sizeListSede());
		        manager.eliminarEmpleado(indicee);
		        view.showText("sede eliminada exitosamente.");
		        persistence.guardarSedesEnArchivo_json(manager.getSedes());

				break;

			case 0:
				view.showText("¡Hasta luego!");
				break;
			default:
				view.showText("Opción no válida. Intente nuevamente.");
			}
		} while (opcion !=0);
		
	}
	
	private void menuEmpleado() {
		int opcion; // Variable para almacenar la opción seleccionada por el usuario
		do {
			view.mostrarMenuEmpleados(); // Llamada al método que muestra el Menu_Estudiante
			opcion = view.leerOpcion(); // Llamada al método que lee la opción ingresada por el usuario

			switch (opcion) {
			case 1:
				manager.verEmpleadosRegistrados();
				break;

			case 2:

				view.showText("=== Registrar Empleado ===");

				// Verificar si hay personas registradas
				if (manager.getPersonas().isEmpty()) {
					view.showText("No hay personas registradas. Registre al menos una persona antes de crear un empleado.");
					return;
				}

				// Declaración de variables para almacenar los datos del empleado
				Cargo cargo = null;

				// Mostrar opciones de selección de cargo
				view.showText("Seleccione el cargo:");
				view.showText("1. DIRECTIVO");
				view.showText("2. ASISTENCIAL");
				view.showText("3. OPERATIVO");

				// Obtener la selección de cargo del usuario
				int opcionCargo = view.leerOpcion();

				// Validar y asignar el cargo
				switch (opcionCargo) {
				case 1:
					cargo = Cargo.DIRECTIVO;
					break;
				case 2:
					cargo = Cargo.ASISTENCIAL;
					break;
				case 3:
					cargo = Cargo.OPERATIVO;
					break;
				default:
					view.showText("Opción de cargo no válida. Por favor, seleccione una opción válida.");
					return;
				}

				// Mostrar la lista de personas existentes
				view.showText("Personas registradas:");
				for (int i = 0; i < manager.getPersonas().size(); i++) {
					view.showText((i + 1) + ". " + manager.getPersonas().get(i).getNombre_apellido_persona());
				}

				// Obtener la selección de la persona asociada al cargo
				view.showText("Seleccione una persona de la lista (1-" + manager.getPersonas().size() + "): ");
				int opcionPersona = view.leerOpcion();

				// Validar la selección de persona
				if (opcionPersona < 1 || opcionPersona > manager.getPersonas().size()) {
					view.showText("Selección de persona no válida. Por favor, seleccione una persona válida.");
					return;
				}

				// Obtener la persona seleccionada
				Persona personaSeleccionada = manager.getPersonas().get(opcionPersona - 1);

				// Verificar si la persona ya está asociada como empleado
				for (Empleado empleado : manager.getEmpleados()) {
					if (empleado.getCargo() == cargo) {
						empleado.getLista_personas_cargo().add(personaSeleccionada);
						view.showText("Persona registrada exitosamente como empleado.");
						persistence.guardarEmpleadosEnArchivo_json(manager.getEmpleados());
						return;
					}
				}

				// Si no se encontró un empleado con el mismo cargo, se crea uno nuevo
				Empleado nuevoEmpleado = new Empleado(cargo, new ArrayList<>());
				nuevoEmpleado.getLista_personas_cargo().add(personaSeleccionada);
				manager.getEmpleados().add(nuevoEmpleado);

				view.showText("Empleado registrado exitosamente.");
				persistence.guardarEmpleadosEnArchivo_json(manager.getEmpleados());

				break;

			case 0:
				view.showText("¡Hasta luego!");
				break;
			default:
				view.showText("Opción no válida. Intente nuevamente.");
			}
		} while (opcion !=0);
		
	}
	
	private void menuPersona() {
		int opcion; // Variable para almacenar la opción seleccionada por el usuario
		do {
			view.mostrarMenuPersonas(); // Llamada al método que muestra el Menu_Estudiante
			opcion = view.leerOpcion(); // Llamada al método que lee la opción ingresada por el usuario

			switch (opcion) {
			case 1:
				manager.verPersonasRegistradas();
				break;

			case 2:

				view.showText("=== Registrar Persona ===");

				// Declaración de variables para almacenar los datos del municipio
				String nombre_persona = null;
				String id_persona = null;

				// Ciclo para validar y registrar los datos de la persona
				while (true) {
					if (nombre_persona == null) {
						view.showText("nombre persona: ");
						nombre_persona = view.leerCadenaNoVaciaTexto().toUpperCase();

					} // Si el codigo de ls persona aún no se ha ingresado
					else if (id_persona == null) {
						view.showText("número identificación persona: ");
						id_persona = view.leerCodigoNumerico();

						// Validar si la persona ya está registrada por id
						for (Persona persona : manager.getPersonas()) {
							if (persona.getId_persona().equals(id_persona)) {
								view.showText("La persona con este id ya está registrada.");
								id_persona = null; // Reiniciar para volver a pedir el dato
								break;
							}
						}
					}

					// Si se han ingresado todos los datos requeridos, registrar la persona
					if (nombre_persona != null && id_persona != null) {
						manager.registrarPersona(id_persona, nombre_persona, "N");
						view.showText("Persona registrada exitosamente.");
						
						persistence.guardarPersonasEnArchivo_json(manager.getPersonas());
						
						break; // Salir del bucle en caso de éxito
					}
				}

				break;
				
			case 3:

				break;

			case 4:
				
				break;

			case 0:
				view.showText("¡Hasta luego!");
				break;
			default:
				view.showText("Opción no válida. Intente nuevamente.");
			}
		} while (opcion !=0);
	
		
	}
	
	private void Asociar_Sede_Empresa() {
		persistence.cargarEmpresasDesdeArchivo_json(manager.getEmpresas()); // Cargar las empresa registradas desde el archivo JSON

		view.showText("=== ASOCIAR SEDE A EMPRESA===");

		if (manager.getSedes().isEmpty()) {
			view.showText("No hay sedes registradas.");
			return;
		}
		manager.verSedesRegistradas();

		view.showText("Ingrese el índice de la sede que desea asociar a la empresa: ");
		int indiceSede = view.leerIndiceValido(manager.sizeListSede());

		Sede sedeSeleccionada = manager.getSedes().get(indiceSede);

		// Verificar si la sede ya está asociada a alguna empresa
		for (Empresa empresa : manager.getEmpresas()) {
			for (Sede sede : empresa.getLista_sedes_empresa()) {
				if (sede.getCodigo_sede().equals(sedeSeleccionada.getCodigo_sede())) {
					view.showText("La sede ya está asociada a una Empresa.");
					return;
				}
			}
		}

		if (manager.getEmpresas().isEmpty()) {
			view.showText("No hay empresas registradas.");
			return;
		}
		manager.verEmpresasRegistradas();

		view.showText("Ingrese el índice de la empresa para la que desea asociar la sede: ");
		int indiceEmpresa = view.leerIndiceValido(manager.sizeListEmpresas());

		Empresa empresaSeleccionada = manager.getEmpresas().get(indiceEmpresa);

		// Verificar si la sede ya está asociada a la empresa seleccionada
		for (Sede sede : empresaSeleccionada.getLista_sedes_empresa()) {
			if (sede.getCodigo_sede().equals(sedeSeleccionada.getCodigo_sede())) {
				view.showText("La sede ya está asociada a la empresa.");
				return;
			}
		}

		empresaSeleccionada.getLista_sedes_empresa().add(sedeSeleccionada);
		view.showText("Sede asociada exitosamente a la Empresa");
		persistence.guardarEmpresasEnArchivo_json(manager.getEmpresas());
	}

	private void desasociarSedeEmpresa() {
		view.showText("=== Desasociar sede de empresa===");

        if (manager.getEmpresas().isEmpty()) {
        	view.showText("No hay empresas registradas.");
            return;
        }

        // Mostrar la lista de empresas
        manager.verEmpresasRegistradas();
        persistence.cargarEmpresasDesdeArchivo_json(manager.getEmpresas());

        view.showText("Ingrese el índice de la empresa de la que desea desasociar una sede: ");
        int indiceEmpresa = view.leerIndiceValido(manager.sizeListEmpresas());

        Empresa empresaSeleccionada = manager.getEmpresas().get(indiceEmpresa);

        if (empresaSeleccionada.getLista_sedes_empresa().isEmpty()) {
        	view.showText("No hay sedes asociadas a esta empresa.");
            return;
        }

        // Mostrar la lista de sedes de la empresa seleccionado
        view.showText("Sedes asociadas a la empresa" + empresaSeleccionada.getNombre_empresa() + ":");
        for (int i = 0; i < empresaSeleccionada.getLista_sedes_empresa().size(); i++) {
            Sede sede = empresaSeleccionada.getLista_sedes_empresa().get(i);
            view.showText(i + ": " + sede.getNombre_sede());
        }

        view.showText("Ingrese el índice de la sede que desea desasociar de la empresa: ");
        int indiceSede = view.leerIndiceValido(empresaSeleccionada.getLista_sedes_empresa().size());

        Sede sedeSeleccionada = empresaSeleccionada.getLista_sedes_empresa().get(indiceSede);

        // Desasociar la sede de la empresa
        empresaSeleccionada.getLista_sedes_empresa().remove(sedeSeleccionada);
        view.showText("Sede desasociada exitosamente de la empresa");
        persistence.guardarEmpresasEnArchivo_json(manager.getEmpresas());
    }
	
	private void ver__Sedes_Empresa() {
		view.showText("=== Sedes de la Empresa ===");

		if (manager.getEmpresas().isEmpty()) {
			view.showText("No hay empresas registradas.");
			return;
		}
		manager.verEmpresasRegistradas();
		persistence.cargarEmpresasDesdeArchivo_json(manager.getEmpresas());

		view.showText("Ingrese el índice de la Empresa de la cual desea ver las sedes registradas: ");
		int indiceEmpresa = view.leerIndiceValido(manager.sizeListEmpresas());

		Empresa empresaSeleccionada = manager.getEmpresas().get(indiceEmpresa);

		if (empresaSeleccionada.getLista_sedes_empresa().isEmpty()) {
			view.showText("No hay sedes en esta Empresa");
		} else {
			view.showText("Sedes asociadas a " + empresaSeleccionada.getNombre_empresa() + ":");
			for (Sede sede : empresaSeleccionada.getLista_sedes_empresa()) {
				view.showText("- " + sede.getNombre_sede() + " (" + sede.getCodigo_sede() + ")" + " ( Sede Principal: "
						+ sede.getSede_principal() + ")");
			}
		}
	}
	
	private void elegir_sede_principal_empresa() {
		view.showText("=== ELEGIR SEDE PRINCIPAL DE LA EMPRESA===");

        if (manager.getEmpresas().isEmpty()) {
        	view.showText("No hay empresas registradas.");
            return;
        }
        manager.verEmpresasRegistradas();

        view.showText("Ingrese el índice de la empresa de la que desea seleccionar la sede principal: ");
        int indiceEmpresa = view.leerIndiceValido(manager.sizeListEmpresas());

        Empresa empresaSeleccionada = manager.getEmpresas().get(indiceEmpresa);

        if (empresaSeleccionada.getLista_sedes_empresa().isEmpty()) {
        	view.showText("La empresa seleccionada no tiene sedes asociadas.");
            return;
        }

        view.showText("sedes de " + empresaSeleccionada.getNombre_empresa() + ":");
        for (int i = 0; i < empresaSeleccionada.getLista_sedes_empresa().size(); i++) {
        	view.showText(i + ". " + empresaSeleccionada.getLista_sedes_empresa().get(i).getNombre_sede());
        }

        view.showText("Ingrese el índice de la sede que desea elegir como sede principal de la empresa: ");
        int indiceSede = view.leerIndiceValido(empresaSeleccionada.getLista_sedes_empresa().size());

        Sede sedeSeleccionada = empresaSeleccionada.getLista_sedes_empresa().get(indiceSede);

        if ("S".equals(sedeSeleccionada.getSede_principal())) {
        	view.showText("La sede seleccionada ya es sede principal de una empresa");
        } else {

            // Verificar si otra sede ya es la principal de la empresa
            boolean sedePrincipalYaElegida = false;
            for (Sede sede : empresaSeleccionada.getLista_sedes_empresa()) {
                if (!sede.equals(sedeSeleccionada) && "S".equals(sede.getSede_principal())) {
                    sedePrincipalYaElegida = true;
                    break;
                }
            }

            if (sedePrincipalYaElegida) {
            	view.showText("Ya se ha elegido otra sede principal para la empresa seleccionada");
            } else {
                sedeSeleccionada.setSede_principal("S");
                view.showText("sede seleccionada como sede ppal de la empresa");
                persistence.guardarEmpresasEnArchivo_json(manager.getEmpresas());

            }
        }
    }
	
	private void modificar_sede_principal_empresa() {
		view.showText("=== MODIFICAR SEDE PRINCIPAL DE LA EMPRESA ===");

        if (manager.getEmpresas().isEmpty()) {
        	view.showText("No hay empresas registradas.");
            return;
        }

        // Mostrar la lista de empresas
        manager.verEmpresasRegistradas();

        view.showText("Ingrese el índice de la empresa para la que desea modificar la sede principal: ");
        int indiceEmpresa = view.leerIndiceValido(manager.getEmpresas().size());

        Empresa empresaSeleccionada = manager.getEmpresas().get(indiceEmpresa);

        if (empresaSeleccionada.getLista_sedes_empresa().isEmpty()) {
        	view.showText("La empresa seleccionada no tiene sedes asociadas");
            return;
        }

        view.showText("Sedes de " + empresaSeleccionada.getNombre_empresa() + ":");
        for (int i = 0; i < empresaSeleccionada.getLista_sedes_empresa().size(); i++) {
        	view.showText(i + ". " + empresaSeleccionada.getLista_sedes_empresa().get(i).getNombre_sede());
        }

        view.showText("Ingrese el índice de la sedes que desea elegir como nueva sede principal de la empresa: ");
        int indiceSede = view.leerIndiceValido(empresaSeleccionada.getLista_sedes_empresa().size());

        Sede nuevaSedePrincipal = empresaSeleccionada.getLista_sedes_empresa().get(indiceSede);

        // Cambiar- actualizar la sede pincipal de la empresa
        for (Sede sede : empresaSeleccionada.getLista_sedes_empresa()) {
            if ("S".equals(sede.getSede_principal())) {
                sede.setSede_principal("N");
                break;
            }
        }
        nuevaSedePrincipal.setSede_principal("S");
        view.showText("Sede actualizada/ seleccionada como sede principal de la empresa.");
        persistence.guardarEmpresasEnArchivo_json(manager.getEmpresas());

    }

}
