package edu.pucmm.ce.main;


import edu.pucmm.ce.encapsulacion.Estudiante;
import edu.pucmm.ce.servicios.DB;
import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;


public class Controlador {

    // Declaración para simplificar el uso del motor de template Thymeleaf.
    public static String renderThymeleaf(Map<String, Object> model, String templatePath) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void templateThymeleaf(){

        /**
         * http://localhost:4567/index           listado de los estudiantes
         */
        get("/listadoEstudiante", (request, response) -> {
            List<Estudiante> listaEstudiante = DB.getInstancia().getEstudiantes();
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("listaEstudiante", listaEstudiante);
           // modelo.put("titulo", "Enviado desde jetty");
            return renderThymeleaf(modelo,"/listado");
        });


        get("/registrarEstudiante", (request, response) ->{
            Map<String, Object> modelo = new HashMap<>();
            Estudiante estudiante = new Estudiante();
            modelo.put("estudiante", estudiante);
            modelo.put("accionFormulario", "Registrar estudiante");
            return renderThymeleaf(modelo,"/formulario");
        });

        get("/editarEstudiante", (request, response) ->{

            Estudiante estudiante = DB.getInstancia().getEstudianteByMatricula(request.queryParams("matricula") );

            if(estudiante.getMatricula().equalsIgnoreCase(""))
                response.redirect("/listadoEstudiante");

            Map<String, Object> modelo = new HashMap<>();
            modelo.put("estudiante", estudiante);
            modelo.put("accionFormulario", "Editar a estudiante: " + estudiante.getNombre());
            return renderThymeleaf(modelo,"/formulario");
        });

        get("/borrarEstudiante", (request, response) ->{
            DB.getInstancia().deleteEstudianteByMatricula(request.queryParams("matricula") );
            response.redirect("/listadoEstudiante");
            return response;
        });




    }


}
