/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dao.DirectorDAO;
import dao.GestionDao;
import dao.PeliculaDAO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Director;
import model.Pelicula;

/**
 *
 * @author alex
 */
public class VentanaModificarPelicula extends javax.swing.JDialog {
    private String titulo;
    /**
     * Creates new form VentanaModificarPelicula
     */
    public VentanaModificarPelicula(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        lblOculto2.setVisible(false);
        
    }
    
    public VentanaModificarPelicula(java.awt.Frame parent, boolean modal, String titulo) {
        super(parent, modal);
        initComponents();
        this.titulo = titulo;
        try {
            cargarDesplegable();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaModificarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblOculto2.setVisible(false);
    }
    
    public void cargarDesplegable() throws SQLException{
        GestionDao gDao = new GestionDao();
        DirectorDAO dDao = new DirectorDAO();
        gDao.conectar();
        ArrayList<Director> listaDirectores = dDao.extraerDirectores();
        gDao.desconectar();
        cbxDirectores.removeAllItems();
        for(int i = 0; i < listaDirectores.size(); i++){
            cbxDirectores.addItem(listaDirectores.get(i).getNombre() + " " + listaDirectores.get(i).getApellidos());
            
        }
        
    }
    
    public Pelicula crearPeliculaModificada() throws SQLException{
        PeliculaDAO pDao = new PeliculaDAO();
        Pelicula peliculaModificada = new Pelicula();
        peliculaModificada.setPais(txtNuevoPais.getText());
        peliculaModificada.setDuracion(Integer.parseInt(txtNuevaDuracion.getText()));
        peliculaModificada.setGenero(txtNuevoGenero.getText());
        DirectorDAO dDao = new DirectorDAO();
        Director directorObjeto = dDao.obtenerDirectorModificarEliminar(cbxDirectores.getSelectedItem().toString());
        peliculaModificada.setIddirector(dDao.obteneriddirector(directorObjeto));
        peliculaModificada.setTitulo(titulo);
        return peliculaModificada;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblModificarPelicula = new javax.swing.JLabel();
        lblNuevoGenero = new javax.swing.JLabel();
        txtNuevoGenero = new javax.swing.JTextField();
        lblNuevoPais = new javax.swing.JLabel();
        txtNuevoPais = new javax.swing.JTextField();
        lblNuevaDuracion = new javax.swing.JLabel();
        txtNuevaDuracion = new javax.swing.JTextField();
        lblNuevoDirector = new javax.swing.JLabel();
        cbxDirectores = new javax.swing.JComboBox<>();
        btnModificarPelicula = new javax.swing.JButton();
        lblOculto2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblModificarPelicula.setFont(new java.awt.Font("Ubuntu", 3, 14)); // NOI18N
        lblModificarPelicula.setText("MODIFICAR PELICULA");

        lblNuevoGenero.setText("Nuevo Género:");

        lblNuevoPais.setText("Nuevo País:");

        txtNuevoPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNuevoPaisActionPerformed(evt);
            }
        });

        lblNuevaDuracion.setText("Nueva Duración:");

        lblNuevoDirector.setText("Nuevo Director:");
        lblNuevoDirector.setToolTipText("");

        cbxDirectores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnModificarPelicula.setText("Modificar Película");
        btnModificarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPeliculaActionPerformed(evt);
            }
        });

        lblOculto2.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNuevaDuracion, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                        .addGap(271, 271, 271))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblModificarPelicula, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblNuevoPais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblNuevoGenero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNuevoPais, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNuevoGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNuevaDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNuevoDirector, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(271, 271, 271))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbxDirectores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(lblOculto2))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(135, 135, 135)
                            .addComponent(btnModificarPelicula))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblModificarPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNuevoPais, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNuevoPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNuevoGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNuevoGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNuevaDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNuevaDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNuevoDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxDirectores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblOculto2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificarPelicula)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPeliculaActionPerformed
        PeliculaDAO pDao = new PeliculaDAO();
        if(txtNuevoPais.getText().isEmpty() || txtNuevaDuracion.getText().isEmpty() || txtNuevoGenero.getText().isEmpty() || cbxDirectores.getSelectedItem() == null){
                lblOculto2.setText("Debe rellenar todos los campos.");
                lblOculto2.setVisible(true);
                
            }else if(pDao.parsearStringInteger(txtNuevaDuracion.getText()) != true){
                lblOculto2.setText("Introduzca un número valido en Duración.");
                lblOculto2.setVisible(true);
                
            }else{
                try {
            GestionDao gDao = new GestionDao();
            Pelicula peliculaModificada = crearPeliculaModificada();
            VentanaPrincipal vp = new VentanaPrincipal();
            boolean existe = pDao.existePelicula(peliculaModificada);
           
            try {
                String update = "UPDATE pelicula SET iddirector = ?, pais = ?, duracion = ?, genero = ? where titulo = ?;";
                gDao.conectar();
                PreparedStatement ps = GestionDao.conexion.prepareStatement(update);
                ps.setInt(1, peliculaModificada.getIddirector());
                ps.setString(2, peliculaModificada.getPais());
                ps.setInt(3, peliculaModificada.getDuracion());
                ps.setString(4, peliculaModificada.getGenero());
                ps.setString(5, titulo);
                    
                ps.executeUpdate();
                ps.close();
                gDao.desconectar();
                vp.crearAviso("Película Modificada.");
            } catch (SQLException ex) {
                Logger.getLogger(VentanaModificarDirector.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaModificarDirector.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            }
        
        
    }//GEN-LAST:event_btnModificarPeliculaActionPerformed

    private void txtNuevoPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuevoPaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNuevoPaisActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaModificarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaModificarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaModificarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaModificarPelicula.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaModificarPelicula dialog = new VentanaModificarPelicula(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(VentanaModificarPelicula.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificarPelicula;
    private javax.swing.JComboBox<String> cbxDirectores;
    private javax.swing.JLabel lblModificarPelicula;
    private javax.swing.JLabel lblNuevaDuracion;
    private javax.swing.JLabel lblNuevoDirector;
    private javax.swing.JLabel lblNuevoGenero;
    private javax.swing.JLabel lblNuevoPais;
    private javax.swing.JLabel lblOculto2;
    private javax.swing.JTextField txtNuevaDuracion;
    private javax.swing.JTextField txtNuevoGenero;
    private javax.swing.JTextField txtNuevoPais;
    // End of variables declaration//GEN-END:variables
}
