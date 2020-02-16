/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import excepciones.DirectorException;
import idao.IDirector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Director;
import model.Pelicula;
import views.Aviso;
import views.VentanaModificarDirector;
import views.VentanaPrincipal;

/**
 *
 * @author alex
 */
public class DirectorDAO implements IDirector{
    
    //DEVUELVE UN ARRAYLIST CON TODOS LOS DIRECTORES OBJETO
    @Override
    public ArrayList<Director> mostrarDirectores() throws SQLException, DirectorException {
        ArrayList<Director> listaDirectores = new ArrayList<Director>();
        String consulta = "SELECT * FROM director;";
        Statement st = GestionDao.conexion.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            Director d =new Director();
            d.setNombre(rs.getString("nombre"));
            d.setApellidos(rs.getString("apellidos"));
                        
            listaDirectores.add(d);
        }
        
        rs.close();
        st.close();
                
        return listaDirectores;
    }
    
    //INSERTA UN DIRECTOR EN LA BD
    @Override
    public void insertarDirector(Director d) throws DirectorException, SQLException {
        GestionDao gDao = new GestionDao();
        VentanaPrincipal vp = new VentanaPrincipal();
        DirectorDAO dDao = new DirectorDAO();
        boolean insertar = existeDirector(d);
        
        if(insertar){
            vp.crearAviso("El director indicado ya existe.");
            
                               
        }else{gDao.conectar();
            String insert = "Insert into director(nombre,apellidos) values (?,?)";
            
            PreparedStatement ps = GestionDao.conexion.prepareStatement(insert);
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getApellidos());
        
            ps.executeUpdate();
            
            ps.close();
            gDao.desconectar();
            vp.crearAviso("Director creado con éxito.");
            System.out.println("Director añadido con éxito.");
            
        }
        
        
    }
    
    //MODIFICA UN DIRECTOR EN LA BD LLAMANDO A LA VISTA VENTANAMODIFICARDIRECTOR
    @Override
    public void modificarDirector(String directorString) throws SQLException{
        
       String apellidos = obtenerDirectorModificarEliminar(directorString).getApellidos();
       VentanaPrincipal vp = new VentanaPrincipal();
       vp.crearVentanaModificarDirector(apellidos);
           
    }
    
    //ELIMINA UN DIRECTOR EN LA BD
    @Override
    public void eliminarDirector(String directorEliminarString) throws SQLException{
        Director director = obtenerDirectorModificarEliminar(directorEliminarString);
        String nombreEliminar = director.getNombre();
        String apellidosEliminar = director.getApellidos();
        GestionDao gDao = new GestionDao();
                              
        director.setIddirector(obteneriddirector(director));
        
        boolean tiene = comprobarSiTienePeliculas(director);
        
        if(tiene == false){
            
            VentanaPrincipal vp =  new VentanaPrincipal();
            String update = "DELETE FROM director where iddirector = ?;";
            gDao.conectar();
            PreparedStatement ps = GestionDao.conexion.prepareStatement(update);
            ps.setInt(1, director.getIddirector());
            ps.executeUpdate();
            ps.close();
            gDao.desconectar();
            vp.crearAviso("Director eliminado.");
            vp.cargarDesplegables();
            
        }else{VentanaPrincipal vp;
            vp = new VentanaPrincipal();
            vp.crearAviso("No se puede eliminar el director porque tiene peliculas asociadas.");
        }
    }
    
    //RETORNA UN TRUE/FALSE SI EXISTE O NO DIRECTOR
    @Override
    public boolean existeDirector(Director d) throws SQLException{
        boolean existe = false;
        GestionDao gDao = new GestionDao();
        gDao.conectar();
        String select = "Select * from director where apellidos='" + d.getApellidos() + "' and nombre='" + d.getNombre() + "'";
        Statement st = GestionDao.conexion.createStatement();
        ResultSet rs = st.executeQuery(select);
            
        while(rs.next()){
             if (rs.getString("nombre").equalsIgnoreCase(d.getNombre()) && rs.getString("apellidos").equalsIgnoreCase(d.getApellidos())){
                System.out.println("Director existente");
                existe = true;
                return existe;
             }  
        }
            
        rs.close();
        st.close();
        gDao.desconectar();
        return existe;
    }
    
    //CREA UN ARRAYLIST CON LOS DIRECTORES EXISTENTES EN LA BD
    public ArrayList<Director> extraerDirectores() throws SQLException{
        ArrayList<Director> listaDirectores = new ArrayList<Director>();
        String consulta = "SELECT * FROM director;";
        try {Statement st = GestionDao.conexion.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            System.out.println("Extracción correcta.");
            while(rs.next()){
                Director d = new Director();
                d.setNombre(rs.getString("nombre"));
                d.setApellidos(rs.getString("apellidos"));
                listaDirectores.add(d);
            }
            st.close();
            rs.close();
        }catch (NullPointerException ex){
            System.out.println("Error de extracción");
        }

        return listaDirectores;
    }
    
    //DEVUELVE UN OBJETO DIRECTOR PASANDOLE POR PARÁMETRO EL NOMBRE COMPLETO STRING
    public Director obtenerDirectorModificarEliminar(String director){
        
        String[] directorSplit = director.split(" ");
        Director directorModificar = new Director();
        directorModificar.setNombre(directorSplit[0]);
        String apellidos = "";
        for(int i = 1; i < directorSplit.length; i++){
            apellidos += directorSplit[i] + " ";
        }
        directorModificar.setApellidos(apellidos.trim());
        return directorModificar;
    }
    
    //DEVUELVE TRUE SI TIENE PELÍCULAS
    public boolean comprobarSiTienePeliculas(Director director)throws SQLException{
        boolean tiene = false;
        int idDirector = director.getIddirector();
        GestionDao gDao = new GestionDao();
        gDao.conectar();
        String consulta = "SELECT titulo FROM pelicula WHERE iddirector=" + idDirector + ";";
        Statement st = GestionDao.conexion.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        
        while(rs.next()){
            tiene = true;
            return tiene;   
        }
        gDao.desconectar();
        return tiene;
    }
    
    //DEVUELVE UN INT CON EL IDDIRECTOR PASANDOLE POR PARÁMETRO UN OBJETO DIRECTOR
    public int obteneriddirector(Director directorObjeto)throws SQLException{
        GestionDao gDao = new GestionDao();
        String directorNombre = directorObjeto.getNombre();
        String directorApellidos = directorObjeto.getApellidos();
        gDao.conectar();
        String consulta = "SELECT iddirector FROM director WHERE nombre='" + directorNombre + "' AND apellidos='" + directorApellidos + "';";
        Statement st = GestionDao.conexion.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            directorObjeto.setIddirector(rs.getInt("iddirector"));
            System.out.println(directorObjeto.getIddirector());         
        }
        gDao.desconectar();
            
        int iddirector = directorObjeto.getIddirector();
    
        return iddirector;
    }
    
    //DEVUELVE UN STRING CON EL NOMBRE COMPLETO DEL DIRECTOR PASANDOLE POR PARÁMETRO UN INT CON EL IDDIRECTOR
    public String obtenerdirector(int iddirector)throws SQLException{
        GestionDao gDao = new GestionDao();
        String nombreDirector = "";
        Director director = new Director();
        gDao.conectar();
        String consulta = "SELECT * FROM director WHERE iddirector=" + iddirector + ";";
        Statement st = GestionDao.conexion.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            director.setNombre(rs.getString("nombre"));
            director.setApellidos(rs.getString("apellidos"));
            nombreDirector = director.getNombre() + " " + director.getApellidos();
            System.out.println(nombreDirector);         
        }
        gDao.desconectar();
            
        return nombreDirector;
    }
    
    //DEVUELVE EL INT IDDIRECTOR PASANDOLE POR PARÁMETRO EL NOMBRE COMPLETO STRING
    public int obtenerIdDirector(String director)throws SQLException{
        GestionDao gDao = new GestionDao();
        Director d = new Director();
        DirectorDAO dDao = new DirectorDAO();
        d = dDao.obtenerDirectorModificarEliminar(director);
        String nombre = d.getNombre();
        String apellidos = d.getApellidos();
        gDao.conectar();
        String consulta = "SELECT * FROM director WHERE nombre='" + nombre + "' AND apellidos='" + apellidos + "';";
        Statement st = GestionDao.conexion.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        while(rs.next()){
            d.setIddirector(rs.getInt("iddirector"));
        }
        gDao.desconectar();
            
        return d.getIddirector();
    }
    
}
