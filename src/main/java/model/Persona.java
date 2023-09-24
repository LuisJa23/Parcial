package model;

public class Persona {
	
    private String id_persona;
    private String nombre_apellido_persona;
    private String empleado_jefe;

    //constructor vacío
    public Persona() {
    }

    public Persona(String id_persona, String nombre_apellido_persona, String empleado_jefe) {
        this.id_persona = id_persona;
        this.nombre_apellido_persona = nombre_apellido_persona;
        this.empleado_jefe = empleado_jefe;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre_apellido_persona() {
        return nombre_apellido_persona;
    }

    public void setNombre_apellido_persona(String nombre_apellido_persona) {
        this.nombre_apellido_persona = nombre_apellido_persona;
    }

    public String getEmpleado_jefe() {
        return empleado_jefe;
    }

    public void setEmpleado_jefe(String empleado_jefe) {
        this.empleado_jefe = empleado_jefe;
    }

    

    @Override
    public String toString() {
        return "Id Persona: " + id_persona
                + ", Nombres y Apellidos Persona: " + nombre_apellido_persona
                + ", Empleado jefe (S/N): " + empleado_jefe;
    }
}
