package edu.pucmm.ce.servicios;

import edu.pucmm.ce.encapsulacion.Estudiante;

import java.util.ArrayList;

public class DB {  //esta clase simulara ser una "base de datos"
    ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private static DB db = null;


    public static DB getInstancia(){
        if(db == null){
            db = new DB();
        }
        return db;
    }

    DB(){
        estudiantes.add( new Estudiante("20132039", "Sarahaime", "Rodriguez", "809-917-6445"));
        estudiantes.add( new Estudiante("20142039", "Sarahaimes", "Rodriguez", "809-917-6414"));
        estudiantes.add( new Estudiante("20152039", "Sarahaime", "Torres", "809-917-6415"));
        estudiantes.add( new Estudiante("20130191", "Edgar", "Gutierrez", "809-999-9999"));
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void addEstudiante(Estudiante estudiante){
        estudiantes.add(estudiante);
    }

    public void deleteEstudianteByMatricula(String matricula){
        for(Estudiante estudiante:estudiantes){
            if(estudiante.getMatricula().equalsIgnoreCase(matricula)) {
                estudiantes.remove(estudiante);
                return;
            }
        }
    }

    public Estudiante getEstudianteByMatricula(String matricula){
        for(Estudiante estudiante:estudiantes){
            if(estudiante.getMatricula().equalsIgnoreCase( matricula) )
                return estudiante;
        }

        return new Estudiante();
    }

    public void updateEstudiante(Estudiante viejo, Estudiante actualizado){
        estudiantes.remove(viejo);
        estudiantes.add(actualizado);
    }

}
