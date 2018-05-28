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
        get("/listado", (request, response) -> {
            List<Estudiante> listaEstudiante = DB.getInstancia().getEstudiantes();
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("listaEstudiante", listaEstudiante);
            modelo.put("titulo", "Enviado desde jetty");
            return renderThymeleaf(modelo,"/listado");

        });
    }



}
