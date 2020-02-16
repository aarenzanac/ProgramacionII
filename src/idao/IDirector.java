/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import excepciones.DirectorException;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Director;


/**
 *
 * @author alex
 */
public interface IDirector {
    
    ArrayList<Director> mostrarDirectores() throws SQLException, DirectorException;
    void insertarDirector(Director d) throws DirectorException, SQLException;
    void modificarDirector(String apellidos)throws SQLException;
    void eliminarDirector(String directorEliminarString)throws SQLException;
    boolean existeDirector(Director d)throws SQLException;
}
