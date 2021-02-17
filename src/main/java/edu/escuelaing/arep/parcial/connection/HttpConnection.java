package edu.escuelaing.arep.parcial.connection;


import java.io.IOException;


public interface HttpConnection {

    public String getWeatherByCity(String city)  throws  IOException;

}