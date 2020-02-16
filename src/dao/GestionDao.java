/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import idao.IDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class GestionDao implements IDao {
    
    public static Connection conexion;

    @Override
    public void conectar() {
        String url = "jdbc:mysql://localhost:3306/FILMOTECA2";
        String username = "root";
        String pass = "root";
        
        try {
            conexion = DriverManager.getConnection(url, username, pass);
        } catch (SQLException ex) {
            System.out.println("Error de conexión." + ex.getMessage());
        }
    }

    @Override
    public void desconectar() {
        if(conexion != null){
            try {
                conexion.close();
            } catch (SQLException ex) {
                System.out.println("Error al desconectar " + ex.getMessage());;
            }
        }
    }
    
}
