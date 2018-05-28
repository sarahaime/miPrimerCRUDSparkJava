package servicios;

import encapsulacion.Estudiante;

import java.util.ArrayList;

public class DB {
    ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private static DB db = null;

    public static DB getInstancia(){
        if(db == null){
            db = new DB();
        }
        return db;
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

    public void deleteEstudianteByMatricula(int matricula){
        for(Estudiante estudiante:estudiantes){
            if(estudiante.getMatricula() == matricula) estudiantes.remove(estudiante);
        }
    }

    public Estudiante getEstudianteByMatricula(int matricula){
        for(Estudiante estudiante:estudiantes){
            if(estudiante.getMatricula() == matricula) return estudiante;
        }

        return new Estudiante(); //esto nunca va a pasar
    }

    public void updateEstudiante(Estudiante viejo, Estudiante actualizado){
        estudiantes.remove(viejo);
        estudiantes.add(actualizado);
    }

}
