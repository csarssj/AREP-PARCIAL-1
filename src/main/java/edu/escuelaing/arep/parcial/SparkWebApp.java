package edu.escuelaing.arep.parcial;

import static spark.Spark.*;

import edu.escuelaing.arep.parcial.cache.weatherCache;
import edu.escuelaing.arep.parcial.connection.HttpConnection;
import edu.escuelaing.arep.parcial.connection.HttpConnectionImpl;
import spark.Request;
import spark.Response;

import java.io.IOException;

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
        get("/clima", (req, res) -> resultsPage(req, res));
    }

    /**
     * Pagina que da el resultado de los calculos
     * @param req Tiene la informacion de la petición.
     * @param res Tiene la información con la respuesta del servidor.
     * @return String que contiene el codigo generado del HTML
     */
    private static String resultsPage(Request req, Response res) throws IOException {
        //HttpConnection http = new HttpConnectionImpl();
        weatherCache cache = new weatherCache();
        String par = req.queryParams("lugar");
        //String json = http.getWeatherByCity(par);
        String json = cache.getCity(par);
        System.out.println(json);
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "body {text-align: center;"
                + " font-family: \"new century schoolbook\";}"
                + "h2 {text-align: center;}"
                + "p {text-align: center;}"
                + "a {text-align: center;}"
                + "div {text-align: center;}"
                + "form action {text-align: center;}"
                + "</style>"
                + "</head>"
                +"<title>OpenWeather</title>"
                + "<body style=\"background-color:powderblue;\">"
                + "<h2 text-aling =\"center\">El Json de la ciudad es </h2>"
                + "<p>" +json+ "</p>"
                + "<br>"
                + "</body>"
                + "</html>";
    }


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
