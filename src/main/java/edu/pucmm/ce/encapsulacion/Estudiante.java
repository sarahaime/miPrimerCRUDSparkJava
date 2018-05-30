package edu.pucmm.ce.encapsulacion;

public class Estudiante {
    private String matricula;
    private String nombre;
    private String apellido;
    private String telefono;
    private int id;

    private static int idCount = 1;

    public Estudiante(){
        this.nombre = "";
        this.apellido ="";
        this.telefono = "";
        this.matricula = "";
        this.id = -1;
    }

    public Estudiante(String matricula, String nombre, String apellido, String telefono) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.id = idCount;
        idCount++;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
