/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excepciones.DirectorException;
import excepciones.PeliculaException;
import idao.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Director;
import model.Pelicula;
import views.VentanaModificarDirector;
import views.VentanaPrincipal;

/**
 *
 * @author alex
 */
public class PeliculaDAO implements IPelicula{
    
    
    //RETORNA UN ARRAYLIST CON UNA LISTA COMPLETA DE PELÍCULAS
    @Override
    public ArrayList<Pelicula> mostrarPeliculas() throws SQLException, PeliculaException{
        GestionDao gDao = new GestionDao();
        ArrayList<Pelicula> listaPeliculas = new ArrayList<Pelicula>();
        String consulta = "SELECT * FROM pelicula;";
        gDao.conectar();
        Statement st = GestionDao.conexion.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        
        while(rs.next()){
            Pelicula p = new Pelicula();
            p.setTitulo(rs.getString("titulo"));
            p.setPais(rs.getString("pais"));
            p.setDuracion(rs.getInt("duracion"));
            p.setGenero(rs.getString("genero"));
            p.setIddirector(rs.getInt("iddirector"));
            
            listaPeliculas.add(p);
        }
        
        rs.close();
        st.close();
        gDao.desconectar();
                
        return listaPeliculas;
    }
    
    //RETORNA UN ARRAYLIST CON LA LISTA DE PELICULAS FILTRADAS POR DIRECTOR PASANDO POR PARÁMETRO EL IDDIRECTOR
    public ArrayList<Pelicula> mostrarPeliculasPorDirector(int iddirector) throws SQLException, PeliculaException{
        GestionDao gDao = new GestionDao();
        ArrayList<Pelicula> listaPeliculasPorDirector = new ArrayList<Pelicula>();
        String consulta = "SELECT * FROM pelicula where iddirector= " + iddirector + ";";
        gDao.conectar();
        Statement st = GestionDao.conexion.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        
        while(rs.next()){
            Pelicula p = new Pelicula();
            p.setTitulo(rs.getString("titulo"));
            p.setPais(rs.getString("pais"));
            p.setDuracion(rs.getInt("duracion"));
            p.setGenero(rs.getString("genero"));
            p.setIddirector(rs.getInt("iddirector"));
            
            listaPeliculasPorDirector.add(p);
        }
        
        rs.close();
        st.close();
        gDao.desconectar();
        int tamaño = listaPeliculasPorDirector.size();
                
        return listaPeliculasPorDirector;
    }
    
    //RETORNA UN ARRAYLIST CON LA LISTA DE PELICULAS FILTRADAS POR GENERO PASANDO POR PARÁMETRO EL STRING GENERO
    public ArrayList<Pelicula> mostrarPeliculasPorGenero(String genero) throws SQLException, PeliculaException{
        GestionDao gDao = new GestionDao();
        ArrayList<Pelicula> listaPeliculasPorGenero = new ArrayList<Pelicula>();
        String consulta = "SELECT * FROM pelicula where genero='" + genero + "';";
        gDao.conectar();
        Statement st = GestionDao.conexion.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        
        while(rs.next()){
            Pelicula p = new Pelicula();
            p.setTitulo(rs.getString("titulo"));
            p.setPais(rs.getString("pais"));
            p.setDuracion(rs.getInt("duracion"));
            p.setGenero(rs.getString("genero"));
            p.setIddirector(rs.getInt("iddirector"));
            
            listaPeliculasPorGenero.add(p);
        }
        
        rs.close();
        st.close();
        gDao.desconectar();
        int tamaño = listaPeliculasPorGenero.size();
                
        return listaPeliculasPorGenero;
    }
    
    //INSERTA UNA PELICULA EN LA BD
    @Override
    public void insertarPelicula(Pelicula p)throws DirectorException, SQLException {
        GestionDao gDao = new GestionDao();
        VentanaPrincipal vp = new VentanaPrincipal();
        PeliculaDAO pDao = new PeliculaDAO();
        boolean insertar = existePelicula(p);
        
        if(insertar){
            vp.crearAviso("La película indicada ya existe.");
            
                               
        }else{gDao.conectar();
            String insert = "Insert into pelicula(titulo, iddirector, pais, duracion, genero) values (?,?,?,?,?)";
            
            PreparedStatement ps = GestionDao.conexion.prepareStatement(insert);
            ps.setString(1, p.getTitulo());
            ps.setInt(2, p.getIddirector());
            ps.setString(3, p.getPais());
            ps.setInt(4, p.getDuracion());
            ps.setString(5, p.getGenero());
        
            ps.executeUpdate();
            
            ps.close();
            gDao.desconectar();
            vp.crearAviso("Pelicula creada con éxito.");
            System.out.println("Pelicula añadida con éxito.");
        }
    }
    
    //LANZA UNA VENTANA DE MODIFICACIÓN DE PELÍCULA PASANDO POR PARÁMETRO LA PELICULA QUE HAY QUE MODIFICAR
    @Override
    public void modificarPelicula(String peliculaString) throws SQLException{
        VentanaPrincipal vp = new VentanaPrincipal();
        vp.crearVentanaModificarPelicula(peliculaString);
    }
    
    //ELIMINA UNA PELICULA DE LA BD
    @Override
    public void eliminarPelicula(String peliculaEliminarString)throws SQLException {
        GestionDao gDao = new GestionDao();
        VentanaPrincipal vp =  new VentanaPrincipal();
        String update = "DELETE FROM pelicula where titulo = ?;";
        gDao.conectar();
        PreparedStatement ps = GestionDao.conexion.prepareStatement(update);
        ps.setString(1, peliculaEliminarString);
        ps.executeUpdate();
        ps.close();
        gDao.desconectar();
        vp.crearAviso("Pelicula eliminada.");
        vp.cargarDesplegables();
            
    }
    
    //RETORNA TRUE SI LA PELÍCULA EXISTE CON EL MISMO TÍTULO
    @Override
    public boolean existePelicula( Pelicula p) {
        boolean existe = false;
        GestionDao gDao = new GestionDao();
        try {gDao.conectar();
            String select = "Select * from pelicula where titulo='" + p.getTitulo()+ "'";
            Statement st = GestionDao.conexion.createStatement();
            
            ResultSet rs = st.executeQuery(select);
           
            while(rs.next()){
                existe = true;
            }
            
            rs.close();
            st.close();
            gDao.desconectar();
        } catch (SQLException ex) {
            System.out.println("Error comprobando si existe el pelicula.");
        }
        return existe;
    }
    
    //CREA UN ARRAYLIS CON LOS TÍTULOS DE LAS PELÍCULAS (PARA EL CHECKBOX DESPLEGABLE DE MODIFICAR O ELIMINAR PELÍCULA)
    public ArrayList<Pelicula> extraerPeliculas() throws SQLException{
        ArrayList<Pelicula> listaPeliculas = new ArrayList<Pelicula>();
        String consulta = "SELECT * FROM pelicula;";
        try {Statement st = GestionDao.conexion.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            System.out.println("Extracción correcta.");
            while(rs.next()){
                Pelicula p = new Pelicula();
                p.setTitulo(rs.getString("titulo"));
                listaPeliculas.add(p);
            }
            st.close();
            rs.close();
        } catch (SQLException ex) {
            ex.getMessage();
        }catch (NullPointerException ex){
            System.out.println("Error de extracción");
        }

        return listaPeliculas;
    }
    
    //DEVUELVE EL OBJETO PELÍCULA PASANDOLE POR PARÁMETRO EL NOMBRE COMPLETO STRING
    public Pelicula obtenerPeliculaModificarEliminar(String pelicula){
        
        String[] peliculaSplit = pelicula.split(" ");
        Pelicula peliculaModificar = new Pelicula();
        peliculaModificar.setTitulo(peliculaSplit[0].trim());
        return peliculaModificar;
    }
    
    //COMPRUEBA RETORNANDO UN TRUE SI LA DURACION ES UN NÚMERO Y SE PUEDE PARSEAR
    public boolean parsearStringInteger(String duracionString) {
        boolean duracionParseada = false;
        try{
            int duracionInt = Integer.parseInt(duracionString);
            duracionParseada = true;
            
        }catch(NumberFormatException ex){
            System.out.println("Error al convertir a Int.");
        }
                  
        return duracionParseada;
    }
    
    //DEVUELVE UN ARRAYLIST CON LOS GÉNEROS
    public ArrayList<String> extraerGeneros() throws SQLException{
        ArrayList<String> listaGeneros = new ArrayList<String>();
        GestionDao gDao = new GestionDao();
        String consulta = "SELECT genero FROM pelicula GROUP BY genero;";
    
        try {gDao.conectar();
            Statement st = GestionDao.conexion.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            System.out.println("Extracción correcta.");
            while(rs.next()){
                String genero = rs.getString("genero");
                listaGeneros.add(genero);
            }
            st.close();
            rs.close();
            gDao.desconectar();
        } catch (SQLException ex) {
            ex.getMessage();
        }catch (NullPointerException ex){
            System.out.println("Error de extracción");
        }

        return listaGeneros;
    }
}
