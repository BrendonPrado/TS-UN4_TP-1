/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Contato;
import model.ContatoDAO;
import model.Usuario;

/**
 *
 * @author brend
 */
public class ContatoController {
    private ContatoDAO contatoDAO = new ContatoDAO();
    
    public void CadastrarContato(Usuario user,String nome,String telefone,String email,String endereco) throws IOException{
        
        contatoDAO.InsertContato(user, nome, telefone, email, endereco);
}
    public Contato ConverteStringParaContato(String n){
        String nome = String.valueOf(n.substring(0,n.indexOf(";",0)));
        n = n.substring(n.indexOf(';',0)+2);
        String telefone = String.valueOf(n.substring(0,n.indexOf(";",0)));
        n = n.substring(n.indexOf(';',0)+2);
        String email = String.valueOf(n.substring(0,n.indexOf(";",0)));
        n = n.substring(n.indexOf(';',0)+2);
        String endereco = n;
        return new Contato(nome,telefone,email,endereco);
    }    

    public int acharContato(ArrayList<Contato> contatos, Contato contato_achar) {
        int index = 0;
        for (Contato contato : contatos) {
            if(contato.getNome().equals(contato_achar.getNome())){
                return index;
            }
            index++;
        }
        return -1;
    }
	public void editaContato(Usuario user_logado,Contato contato_editar , JTextField jTextField1, JTextField jTextField2, JTextField jTextField3, JTextField jTextField4) throws FileNotFoundException, IOException {
		 ArrayList<Contato> contatos = contatoDAO.SelectAllContato(user_logado);
         int index_editar = acharContato(contatos,contato_editar);
         contatos.get(index_editar).setNome(jTextField1.getText());
         contatos.get(index_editar).setTelefone(jTextField3.getText());
         contatos.get(index_editar).setEmail(jTextField4.getText());
         contatos.get(index_editar).setEndereco(jTextField2.getText());
         
         contatoDAO.AtualizaContatos(user_logado, contatos);
	}
	   
    boolean validarNome(String n){
   if(n.matches("[a-zA-z0-9 áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]+") && n != null){
       return true;
   }
   return false;
}

boolean validarTel(String n){
    if(n.matches("[0-9]+") && n != null){
        return true;
    }
    return false;
}
public static boolean validarEmail(String email)
{
    boolean emailValido = false;
    if (email != null && email.length() > 0) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            emailValido = true;
        }
    }
    return emailValido;
}


public boolean validaCampos(JTextField j1 ,JTextField j2, JTextField j3, JTextField j4,JButton jButton1,Usuario user_logado) throws IOException{
    
    int alerta = 0;
    ArrayList<String> impr = new ArrayList<String>();impr.add("lugar nenhum");impr.add("judas perdeu as botas");impr.add("casa da mãe joana");impr.add("num sei");
    ArrayList<String> erros = new ArrayList<String>();
    
    if(jButton1.getText().equals("Cadastrar")){
        
    if(j1.getText().length() <= 2 || (!validarNome(j1.getText()))|| new ContatoDAO().SelectContatoPorNomeExato(user_logado, j1.getText()) !=null){
        alerta = 1;
        erros.add("O Campo Nome deve conter um nome único na agenda e conter 5 a 15 números! Somente letras de a-z e números de 0-9");
    }if(j3.getText().length() < 8 || j3.getText().length()> 20 || (!validarTel(j3.getText())) ){
        alerta = 1;
        erros.add("O campo telefone deve conter ao menos 8 e no máximo 20!! Somente números  ");
    }if(!validarEmail(j4.getText()) || j4.getText().length() == 0){
        alerta = 1;
        erros.add("Este email não é inválido!!");
    }if(impr.contains(j2.getText()) || j2.getText().length() < 3 || j2.getText().length() > 255 ){
        alerta = 1;
        erros.add("Este endereço é invalido");
    }
    if(alerta==1){
        JOptionPane.showMessageDialog(new JFrame(),gerarMsgErro(erros));    
        return false;
    }
    return true; 
    }else{
         if(j1.getText().length() <= 2 || (!validarNome(j1.getText()))){
        alerta = 1;
        erros.add("O Campo Nome deve conter um nome único na agenda e conter 5 a 15 números! Somente letras de a-z e números de 0-9");
    }if(j3.getText().length() < 8 || j3.getText().length()> 20 || (!validarTel(j3.getText())) ){
        alerta = 1;
        erros.add("O campo telefone deve conter ao menos 8 e no máximo 20!! Somente números  ");
    }if(!validarEmail(j4.getText()) || j4.getText().length() == 0){
        alerta = 1;
        erros.add("Este email não é inválido!!");
    }if(impr.contains(j2.getText()) || j2.getText().length() < 3 || j2.getText().length() > 255 ){
        alerta = 1;
        erros.add("Este endereço é invalido");
    }
    if(alerta==1){
        JOptionPane.showMessageDialog(new JFrame(),gerarMsgErro(erros));    
        return false;
    }
    return true; 
        
    }
}

    public boolean validaCampos(String j1 ,String j2, String j3, String j4,String jButton1,Usuario user_logado) throws IOException{

        int alerta = 0;
        ArrayList<String> impr = new ArrayList<String>();impr.add("lugar nenhum");impr.add("judas perdeu as botas");impr.add("casa da mãe joana");impr.add("num sei");
        ArrayList<String> erros = new ArrayList<String>();

        if(jButton1.equals("Cadastrar")){

            if(j1.length() <= 2 || (!validarNome(j1))|| new ContatoDAO().SelectContatoPorNomeExato(user_logado, j1) !=null){
                alerta = 1;
                erros.add("O Campo Nome deve conter um nome único na agenda e conter 5 a 15 números! Somente letras de a-z e números de 0-9");
            }if(j3.length() < 8 || j3.length()> 20 || (!validarTel(j3)) ){
                alerta = 1;
                erros.add("O campo telefone deve conter ao menos 8 e no máximo 20!! Somente números  ");
            }if(!validarEmail(j4) || j4.length() == 0){
                alerta = 1;
                erros.add("Este email não é inválido!!");
            }if(impr.contains(j2) || j2.length() < 3 || j2.length() > 255 ){
                alerta = 1;
                erros.add("Este endereço é invalido");
            }
            if(alerta==1){
                JOptionPane.showMessageDialog(new JFrame(),gerarMsgErro(erros));
                return false;
            }
            return true;
        }else{
            if(j1.length() <= 2 || (!validarNome(j1))){
                alerta = 1;
                erros.add("O Campo Nome deve conter um nome único na agenda e conter 5 a 15 números! Somente letras de a-z e números de 0-9");
            }if(j3.length() < 8 || j3.length()> 20 || (!validarTel(j3)) ){
                alerta = 1;
                erros.add("O campo telefone deve conter ao menos 8 e no máximo 20!! Somente números  ");
            }if(!validarEmail(j4) || j4.length() == 0){
                alerta = 1;
                erros.add("Este email não é inválido!!");
            }if(impr.contains(j2) || j2.length() < 3 || j2.length() > 255 ){
                alerta = 1;
                erros.add("Este endereço é invalido");
            }
            if(alerta==1){
                JOptionPane.showMessageDialog(new JFrame(),gerarMsgErro(erros));
                return false;
            }
            return true;

        }
    }

String gerarMsgErro(ArrayList<String> erros){
    String erro = "Há campos invalidos:";
    for (Object e : erros) {
        erro+="\n- "+e;
    }
    return erro;
}
public ArrayList<Contato> SelecionaContatosUsuario(Usuario user_logado) throws FileNotFoundException, IOException {
	ArrayList<Contato> contatos = contatoDAO.SelectAllContato(user_logado);
	return contatos;
}
public void AtualizaContatos(Usuario user_logado, ArrayList<Contato> contatos) throws FileNotFoundException, IOException {
	contatoDAO.AtualizaContatos(user_logado, contatos);
}


	
}
