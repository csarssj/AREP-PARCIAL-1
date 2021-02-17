package edu.escuelaing.arep.parcial;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
/**
 * Clase principal donde se inicia la aplicación web construida con spark
 *
 * @author ceseg
 */
public class SparkWebApp
{
    /**
     * Metodo principal que usa sparkWeb y funciones lampda para mapear las vistas
     * dentro de la app
     * @param args
     */
    public static void main(String[] args) {
        port(getPort());
        get("/hello", (req, res) -> "Hello Heroku");
    }

    /**


    /**
     *
     * @return el puerto sobre cual se desplegará la app|
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhot)
    }
}
