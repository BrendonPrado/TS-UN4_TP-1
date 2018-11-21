/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Usuario;
import model.UsuarioDAO;
import view.TelaCadastroUsuarioAgenda;
import view.TelaLogin;

/**
 *
 * @author brend
 */
public class UsuarioController {
    UsuarioDAO userdao = new UsuarioDAO();
    codificador codif = new codificador();
   
    public Usuario autenticarLogin(JTextField j1,JPasswordField jp1) throws IOException{
          Usuario user = userdao.SelectUsuario(j1.getText());
                String s = String.copyValueOf(jp1.getPassword());
                if(user.logar_senha(s)){
                   return user;
                }
                else{
                    return null;
                }
        
    }
    
    public boolean verificarlog(Usuario user) throws IOException{
        if(this.autenticarLogin(user.getNome(),user.getSenha()) != null){
            return true;
        }else{
            return false;
        }
    }
     public Usuario autenticarLogin(String j1,String s) throws IOException{
          Usuario user = userdao.SelectUsuario(j1);
                if(user!=null && user.logar_senha(s)){
                   return user;
                }
                else{
                    return null;
                }
        
    }
     public boolean validarCampos(JTextField j1 , JTextField j2 , JTextField j3) throws IOException{
         int alerta = 0;
         ArrayList<String> erros = new ArrayList<String>();
         
         if((!validarNome(j1.getText())) || new UsuarioDAO().SelectUsuario(j1.getText()) != null || j1.getText().length() > 15 || j1.getText().length() < 5 ){
             alerta = 1;
             erros.add("O Campo Nome deve conter um nome único no sistema e conter 5 a 15 números! Somente letras de a-z e números de 0-9");
         }if((!validarNome(j3.getText())) || j3.getText().length() > 15 || j3.getText().length() < 5){
             alerta = 1;
             erros.add("O Campo senha deve conter 5 a 15 números! Somente letras de a-z e números de 0-9");
         }else if(!j2.getText().equals(j3.getText()) && (!(!validarNome(j3.getText())) || j3.getText().length() > 15 || j3.getText().length() < 5)){
            alerta = 1;
            erros.add("A senha confirmada é diferente ou nula");
     }
          if(alerta==1){
             JOptionPane.showMessageDialog(new JFrame(),gerarMsgErro(erros));    
             return false;
         }
        return true;
     }

    public boolean validarCampos(String j1 , String j2 , String j3) throws IOException{
        int alerta = 0;
        ArrayList<String> erros = new ArrayList<String>();

        if((!validarNome(j1)) || new UsuarioDAO().SelectUsuario(j1) != null || j1.length() > 15 || j1.length() < 5 ){
            alerta = 1;
            erros.add("O Campo Nome deve conter um nome único no sistema e conter 5 a 15 números! Somente letras de a-z e números de 0-9");
        }if((!validarNome(j3)) || j3.length() > 15 || j3.length() < 5){
            alerta = 1;
            erros.add("O Campo senha deve conter 5 a 15 números! Somente letras de a-z e números de 0-9");
        }else if(!j2.equals(j3) && (!(!validarNome(j3)) || j3.length() > 15 || j3.length() < 5)){
            alerta = 1;
            erros.add("A senha confirmada é diferente ou nula");
        }
        if(alerta==1){
            JOptionPane.showMessageDialog(new JFrame(),gerarMsgErro(erros));
            return false;
        }
        return true;
    }

     boolean validarNome(String n) throws IOException{
         if(n.matches("[a-zA-Z0-9]+") && n!=null){
             return true;
         }
         return false;
     }
     
      String gerarMsgErro(ArrayList<String> erros){
             String erro = "Há campos invalidos:";
             for (Object e : erros) {
                 erro+="\n-"+e;
             }
             return erro;
         }
     
     public void CadastrarNovoUsuario(JTextField jTextField1, JTextField jTextField3) {
 		try {
 		    userdao.InsertUsuario(new Usuario(jTextField1.getText(), jTextField3.getText()));
 		    JOptionPane.showMessageDialog(new JFrame(),"Usuário cadastrado");
 		} catch (IOException ex) {
 		    Logger.getLogger(TelaCadastroUsuarioAgenda.class.getName()).log(Level.SEVERE, null, ex);
 		}
 	} 
    
    public boolean redirNaoLog() {
         JFrame j=new TelaLogin();
         JOptionPane.showMessageDialog(new JFrame(),"Faça o login");
         j.setVisible(true);
         return false;
    }

    public void verificarDadosCadastrados() throws IOException {
        ArrayList<Usuario> usuarios = this.userdao.selectUsuarios();
        ArrayList<String> nomes = new ArrayList<>();
        usuarios.forEach(f->nomes.add(f.getNome()));
        File file = new File(".");
        File[] arquivos = file.listFiles();
        for (File arq :arquivos) {
            if(arq.getName().contains("data_u")){
                    if(!nomes.contains(codif.descodifica(arq.getName().substring(6,arq.getName().lastIndexOf("."))))){
                        arq.delete();
                    }
             }
    }
        for (String nome : nomes) {
            if(!new File("data_u"+codif.codifica(nome)+".txt").exists()){
                new File("data_u"+codif.codifica(nome)+".txt").createNewFile();
        }
            
        }
  }
    public boolean validarNomeLogin(JTextField j1, JTextField j2) throws IOException{
        if(j1.getText().equals("")){
             JOptionPane.showMessageDialog(new JFrame(),"Campo Nome vazio");
             return false;
        }else if(new UsuarioDAO().SelectUsuario(j1.getText())==null){
             JOptionPane.showMessageDialog(new JFrame(),"Usuário não existe");
             return false;
         }if(j2.getText().equals("")){
             JOptionPane.showMessageDialog(new JFrame(),"Campo senha vazio");
             return false;
         }    
         return true;
     }

	public void CadastrarNovoUsuario(Usuario joao) throws IOException {
		userdao.InsertUsuario(joao);
	
		
	}

	public void removerUsuario(Usuario joao) throws IOException {
		userdao.delete(joao);
		
	}
    
    
    
    
}
