package edu.pucmm.ce.main;


import edu.pucmm.ce.encapsulacion.Estudiante;
import edu.pucmm.ce.servicios.DB;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

            try {
                Estudiante estudiante = DB.getInstancia().getEstudianteByID(Integer.parseInt(request.queryParams("id")));

                if (estudiante.getMatricula().equalsIgnoreCase(""))
                    response.redirect("/listadoEstudiante");

                Map<String, Object> modelo = new HashMap<>();
                modelo.put("estudiante", estudiante);
                modelo.put("accionFormulario", "Editar a estudiante: " + estudiante.getNombre());
                return renderThymeleaf(modelo, "/formulario");
            }catch (Exception e){
                response.redirect("/registrarEstudiante");
                return "ID no existe como parametro";
            }

        });

        get("/borrarEstudiante", (request, response) ->{
            DB.getInstancia().deleteEstudianteByMatricula(request.queryParams("matricula") );
            response.redirect("/listadoEstudiante");
            return response;
        });


        post("/registrarEstudiante", (request, response) ->{
            Estudiante estudiante = new Estudiante(request.queryParams("matricula"), request.queryParams("nombre"), request.queryParams("apellido"), request.queryParams("telefono"));
            DB.getInstancia().addEstudiante(estudiante);
            response.redirect("/listadoEstudiante");
            return "Estudiante registrado";
        });


        post("/editarEstudiante", (request, response) ->{
            DB.getInstancia().updateEstudianteByID( Integer.parseInt( request.queryParams("id") ),
                    request.queryParams("matricula"), request.queryParams("nombre"),
                    request.queryParams("apellido"), request.queryParams("telefono"));

            response.redirect("/listadoEstudiante");
            return "Estudiante editado";
        });




    }

    private static Object procesarParametros(Request request, Response response){
        System.out.println("Recibiendo mensaje por el metodo: "+request.requestMethod());
        Set<String> parametros = request.queryParams();
        String salida="";

        for(String param : parametros){
            salida += String.format("Parametro[%s] = %s <br/>", param, request.queryParams(param));
        }

        return salida;
    }



}
