/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ContatoController;
import controller.UsuarioController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Contato;
import model.Usuario;

/**
 *
 * @author fabriciogmc
 */
public class TelaListagem extends javax.swing.JFrame {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario user_logado;
    private UsuarioController user_con = new UsuarioController();
    private ContatoController contatoController = new ContatoController();
    /**
     * Creates new form TelaListagem
     */
    public TelaListagem(Usuario user) throws IOException {
       if(user.isLogado()){
                this.user_logado = user;
                initComponents();
                ArrayList<Contato> contatos = contatoController.SelecionaContatosUsuario(this.user_logado);
                if(contatos.size()>0){
                contatos.forEach((contato) -> { 
                    this.list1.add(contato.getNome()+"; "+contato.getTelefone()+"; "+contato.getEmail()+"; "+contato.getEndereco());
           });
                }else{
                    this.list1.add("Não há contatos cadastrados");
                    this.jButton1.setEnabled(false);
                    this.jButton2.setEnabled(false);
                }
            }else{
                this.dispose();
                user_con.redirNaoLog();
            }    
    }

	
    public TelaListagem(){
      Usuario user = new Usuario();
      if(user.isLogado()){
                this.user_logado = user;
                initComponents();
            }else{
                user_con.redirNaoLog();
            }        
    }

   public TelaListagem(Usuario user, ArrayList<Contato> contatos_achados) throws IOException {
         if(user.isLogado()){
                this.user_logado = user;
                initComponents();
                if(contatos_achados.size()>0){
                contatos_achados.forEach((contato) -> { 
                    this.list1.add(contato.getNome()+"; "+contato.getTelefone()+"; "+contato.getEmail()+"; "+contato.getEndereco());
           });
                }else{
                    this.list1.add("Não há contatos cadastrados");
                    this.jButton1.setEnabled(false);
                    this.jButton2.setEnabled(false);
                }
            }else{
               user_con.redirNaoLog();

            }    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        list1 = new java.awt.List();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("Contatos existentes na agenda:");

        list1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                list1ItemStateChanged(evt);
            }
        });

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Excluir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jLabel1)
                    .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void list1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_list1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_list1ItemStateChanged

    @SuppressWarnings("deprecation")
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          try {
            if(contatoController.SelecionaContatosUsuario(user_logado).size()>0){
                if(this.list1.getSelectedItem() != null){
                  new TelaCadastro(this.user_logado,contatoController.ConverteStringParaContato(this.list1.getSelectedItem())).setVisible(true);
                  this.setVisible(false);
             }else{
                    JOptionPane.showMessageDialog(this,"Selecione o contato para editar");
                }
                
            }
            else{
                this.list1.clear();
                JOptionPane.showMessageDialog(this,"Não há contatos!");
                this.list1.add("Não há contatos cadastrados");
            }
        } catch (IOException ex) {
            Logger.getLogger(TelaListagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    @SuppressWarnings("deprecation")
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            if(contatoController.SelecionaContatosUsuario(user_logado).size()>0){
                if(this.list1.getSelectedItem() != null){
                    try {
                    ArrayList<Contato> contatos = contatoController.SelecionaContatosUsuario(user_logado);
                    contatos.remove(this.list1.getSelectedIndex());
                    contatoController.AtualizaContatos(user_logado, contatos);
                    this.list1.clear();
                    if(contatos.size()==0){
                        this.list1.clear();
                        this.list1.add("Não há contatos cadastrados");
                        this.jButton1.setEnabled(false);
                        this.jButton2.setEnabled(false);
                        
                    }else{
                        contatos.forEach((contato) -> {
                            this.list1.add(contato.getNome()+"; "+contato.getTelefone()+"; "+contato.getEmail()+" ;"+contato.getEndereco());
                        });
                    }
                } catch (IOException ex) {
                    Logger.getLogger(TelaListagem.class.getName()).log(Level.SEVERE, null, ex);
                }    
             }else{
                    JOptionPane.showMessageDialog(this,"Selecione o contato para excluir");
                }
                
            }
            else{
                this.list1.clear();
                JOptionPane.showMessageDialog(this,"Não há contatos!");
                this.list1.add("Não há contatos cadastrados");
                
            }
        } catch (IOException ex) {
            Logger.getLogger(TelaListagem.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    }//GEN-LAST:event_jButton2ActionPerformed

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
            Logger.getLogger(TelaListagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TelaListagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TelaListagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TelaListagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaListagem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private java.awt.List list1;
    // End of variables declaration//GEN-END:variables
}
