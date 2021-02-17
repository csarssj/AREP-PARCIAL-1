package edu.escuelaing.arep.parcial.cache;

import edu.escuelaing.arep.parcial.connection.HttpConnection;
import edu.escuelaing.arep.parcial.connection.HttpConnectionImpl;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class weatherCache {
    HttpConnection http = new HttpConnectionImpl();

    private ConcurrentHashMap<String, String> cities = new ConcurrentHashMap<>();


    /**
     * Agrega una ciudad y sus datos a una estructura de datos concurrente
     *
     * @param name
     * @param weather
     */
    public void addCities(String name ,String weather){
        cities.put(name,weather);
    }

    /**
     *
     * @param name nombre de la ciudad a consular
     * @return las estadisticas del clima de la ciudad correspondiente
     * se conserva en el cache de la app
     * @throws IOException
     */
    public String getCity(String name) throws IOException {
        //System.out.println("Entro en cache1");
        if(cities.containsKey(name)) {
            //System.out.println("te caché xd");
            return cities.get(name);
        }else{
            //System.out.println("Entra por aca");
            String weather = http.getWeatherByCity(name);
            cities.put(name,weather);
            return weather;
        }
    }
}
