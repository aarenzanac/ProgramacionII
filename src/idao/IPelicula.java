/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import excepciones.DirectorException;
import excepciones.PeliculaException;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Pelicula;

/**
 *
 * @author alex
 */
public interface IPelicula {
    
    ArrayList<Pelicula> mostrarPeliculas() throws SQLException, PeliculaException;
    void insertarPelicula(Pelicula p)throws DirectorException, SQLException;
    void modificarPelicula(String peliculaString)throws SQLException;
    void eliminarPelicula(String peliculaEliminarString)throws SQLException;
    boolean existePelicula(Pelicula p);
}
