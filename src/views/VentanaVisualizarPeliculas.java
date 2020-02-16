/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dao.DirectorDAO;
import dao.GestionDao;
import dao.PeliculaDAO;
import excepciones.PeliculaException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pelicula;

/**
 *
 * @author alex
 */
public class VentanaVisualizarPeliculas extends javax.swing.JFrame {

    /**
     * Creates new form VentanaVisualizarPeliculas
     */
    int posicion = 0;
    int tamañoArray = 0;
    int iddirector = 0;
    ArrayList<Pelicula> listaPeliculas;
    
    public VentanaVisualizarPeliculas() throws SQLException {
        initComponents();
        this.setLocationRelativeTo(null);
    }
      
    public VentanaVisualizarPeliculas(ArrayList<Pelicula> listaPeliculas) throws SQLException {
        this.listaPeliculas = listaPeliculas;
        initComponents();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        if(posicion == 0){
        btnAnterior.setEnabled(false);
        }
        tamañoArray = listaPeliculas.size();
        DirectorDAO dDao = new DirectorDAO();
        try {
            txtTitulo.setText(listaPeliculas.get(posicion).getTitulo());
            iddirector = listaPeliculas.get(posicion).getIddirector();
            String nombreDirector = dDao.obtenerdirector(listaPeliculas.get(posicion).getIddirector());
            txtDirector.setText(nombreDirector);
            txtPais.setText(listaPeliculas.get(posicion).getPais());
            txtDuracion.setText(Integer.toString(listaPeliculas.get(posicion).getDuracion()));
            txtGenero.setText(listaPeliculas.get(posicion).getGenero());
        }catch(NullPointerException ex){
            System.out.println("Error de array." + ex.getMessage());
        }
        if(tamañoArray == 1){
            btnAnterior.setEnabled(false);
            btnSiguiente.setEnabled(false);
        }
        
    }
    
    //ACCION AL PULSAR EL BOTÓN SIGUIENTE
    public void siguiente(int tamañoArray){
        DirectorDAO dDao = new DirectorDAO();
        if(posicion == tamañoArray-1){
            btnSiguiente.setEnabled(false);                   
        }
        if(posicion < tamañoArray-1 && posicion >= 0){
            btnAnterior.setEnabled(true);
            btnSiguiente.setEnabled(true);
        }
        
        btnAnterior.setEnabled(true);
        PeliculaDAO pdao = new PeliculaDAO();
        GestionDao gDao = new GestionDao();
        try {gDao.conectar();
            gDao.desconectar();
            txtTitulo.setText(listaPeliculas.get(posicion).getTitulo());
            String nombreDirector = dDao.obtenerdirector(listaPeliculas.get(posicion).getIddirector());
            txtDirector.setText(nombreDirector);
            txtPais.setText(listaPeliculas.get(posicion).getPais());
            txtDuracion.setText(Integer.toString(listaPeliculas.get(posicion).getDuracion()));
            txtGenero.setText(listaPeliculas.get(posicion).getGenero());
        } catch (SQLException ex) {
            Logger.getLogger(VentanaVisualizarPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException ex){
            System.out.println("Error de array." + ex.getMessage());
        }
        
    }
    
    //ACCION AL PULSAR EL BOTON ANTERIOR
    public void anterior(int tamañoArray){
        DirectorDAO dDao = new DirectorDAO();
        if(posicion == 0){
            btnAnterior.setEnabled(false);
        }
        if(posicion < tamañoArray-1 && posicion > 0){
            btnAnterior.setEnabled(true);
            btnSiguiente.setEnabled(true);
        }
        PeliculaDAO pdao = new PeliculaDAO();
        GestionDao gDao = new GestionDao();
        try {gDao.conectar();
            gDao.desconectar();
            txtTitulo.setText(listaPeliculas.get(posicion).getTitulo());
            String nombreDirector = dDao.obtenerdirector(listaPeliculas.get(posicion).getIddirector());
            txtDirector.setText(nombreDirector);
            txtPais.setText(listaPeliculas.get(posicion).getPais());
            txtDuracion.setText(Integer.toString(listaPeliculas.get(posicion).getDuracion()));
            txtGenero.setText(listaPeliculas.get(posicion).getGenero());
        } catch (SQLException ex) {
            Logger.getLogger(VentanaVisualizarPeliculas.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException ex){
            System.out.println("Error de array." + ex.getMessage());
        }
    }
    
    
    
    
    
     
    

    /**
     * This method is called from within the constructor toinitialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTituloVentana = new javax.swing.JLabel();
        lblTituloPelicula = new javax.swing.JLabel();
        lblDirector = new javax.swing.JLabel();
        lblPais = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        txtDirector = new javax.swing.JTextField();
        txtPais = new javax.swing.JTextField();
        txtDuracion = new javax.swing.JTextField();
        txtGenero = new javax.swing.JTextField();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTituloVentana.setFont(new java.awt.Font("Lucida Sans", 3, 18)); // NOI18N
        lblTituloVentana.setForeground(new java.awt.Color(13, 10, 155));
        lblTituloVentana.setText("PELÍCULAS:");

        lblTituloPelicula.setText("TÍTULO:");

        lblDirector.setText("DIRECTOR:");

        lblPais.setText("PAIS:");

        lblDuracion.setText("DURACION:");

        lblGenero.setText("GENERO:");

        txtDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDirectorActionPerformed(evt);
            }
        });

        btnAnterior.setText("Anterior");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTituloVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTituloPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDirector))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPais, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPais))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnterior))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCerrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSiguiente)
                                .addGap(76, 76, 76)))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lblTituloPelicula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDirector)
                    .addComponent(lblDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPais, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDuracion, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txtGenero, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lblGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior)
                    .addComponent(btnSiguiente)
                    .addComponent(btnCerrar))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDirectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirectorActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        posicion += 1;
        siguiente(tamañoArray);
        
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        posicion -= 1;
        anterior(tamañoArray);
        
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaVisualizarPeliculas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaVisualizarPeliculas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaVisualizarPeliculas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaVisualizarPeliculas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new VentanaVisualizarPeliculas().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(VentanaVisualizarPeliculas.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
    
    
    
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JLabel lblDirector;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblTituloPelicula;
    private javax.swing.JLabel lblTituloVentana;
    private javax.swing.JTextField txtDirector;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtPais;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
