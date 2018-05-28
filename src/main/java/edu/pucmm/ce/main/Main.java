package edu.pucmm.ce.main;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    public static void main(String[] args){
        //indicando los recursos publicos, con esto se puede acceder a ellos sin hacerle metodos get ni post ni nada de eso
        staticFiles.location("/publico");

        //Linea para agregar la pantalla de debug. En productivo se debe quitar.
        enableDebugScreen();

        //Seteando el puerto en Heroku
        port(getHerokuAssignedPort());

        new Controlador().templateThymeleaf();

    }


    /**
     * Metodo para setear el puerto en Heroku
     * @return
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
