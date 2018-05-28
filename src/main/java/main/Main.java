package main;

import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Set;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;
public class Main {

    public static void main(){


        //indicando los recursos publicos, con esto se puede acceder a ellos sin hacerle metodos get ni post ni nAdad de eso
        staticFiles.location("/publico");

        //Linea para agregar la pantalla de debug. En productivo se debe quitar.
        enableDebugScreen();

        //Seteando el puerto en Heroku
        port(getHerokuAssignedPort());


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
