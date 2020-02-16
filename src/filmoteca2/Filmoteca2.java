/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmoteca2;

import dao.DirectorDAO;
import dao.GestionDao;
import dao.PeliculaDAO;
import excepciones.DirectorException;
import excepciones.PeliculaException;
import java.sql.Connection;
import java.sql.SQLException;
import model.Director;
import model.Pelicula;

/**
 *
 * @author alex
 */
public class Filmoteca2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, PeliculaException, DirectorException {
        // TODO code application logic here..
        GestionDao dao = new GestionDao();
        PeliculaDAO pDao = new PeliculaDAO();
        DirectorDAO dDao = new DirectorDAO();
        System.out.println("Conectando.....");
        dao.conectar();
        System.out.println("Conetado.");
        for(Pelicula p : pDao.mostrarPeliculas()){
            System.out.println("Título: " + p.getTitulo() + " --- País: " + p.getPais() + " --- Duración: " + p.getDuracion() + " --- Género: " + p.getGenero());
        }
        for(Director d : dDao.mostrarDirectores()){
            System.out.println("Nombre: " + d.getNombre()+ " --- Apellidos: " + d.getApellidos());
        }
        System.out.println("Desconectando.....");
        dao.desconectar();
        System.out.println("Desconectado.");
        
        
        Director d = new Director("Alejandro", "Arenzana");
        GestionDao gdao = new GestionDao();
        gdao.conectar();
        DirectorDAO Ddao = new DirectorDAO();
        Ddao.insertarDirector(d);
        
    }
    
}
