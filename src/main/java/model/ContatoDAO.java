/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.codificador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brend
 */
public class ContatoDAO {
    private codificador codif = new codificador();
	private BufferedReader lerArq;
    
    public void InsertContato(Usuario user,String nome, String telefone, String email,String endereco) throws IOException{
        File file = new File("data_u"+codif.codifica(user.getNome())+".txt");
         if(!file.exists()){
            file.createNewFile();
        }
        FileWriter f = new FileWriter(file,true);
        PrintWriter grava = new PrintWriter(f, true);
        
        grava.println(nome+"; "+telefone+"; "+email+"; "+endereco);
        
      
        
        
        grava.close();
        f.close();
        ArrayList<Contato> contatos = SelectAllContato(user);
        this.AtualizaContatos(user, contatos);
    }
    
     public void InsertContatoAtualiza(Usuario user,String nome, String telefone, String email,String endereco) throws IOException{
        File file = new File("data_u"+codif.codifica(user.getNome())+".txt");
         if(!file.exists()){
            file.createNewFile();
        }
        FileWriter f = new FileWriter(file,true);
        PrintWriter grava = new PrintWriter(f, true);
        
        grava.println(nome+"; "+telefone+"; "+email+"; "+endereco);
        
      
        
        
        grava.close();
        f.close();
    }
 
     
    public Contato SelectContatoPorNomeExato(Usuario user,String nome_buscar) throws FileNotFoundException, IOException{
        File file = new File("data_u"+codif.codifica(user.getNome())+".txt");
         if(!file.exists()){
            file.createNewFile();
        }
        FileReader f = new FileReader(file);
        lerArq = new BufferedReader(f);
        
        String linha = lerArq.readLine();
        
        
        while(linha != null){
           Contato contato =ConverteStringParaContato(linha);
           if(contato.getNome().equals(nome_buscar)){
               return contato;
           }
            linha = lerArq.readLine();
        }
        
        f.close();
        lerArq.close();
        return null;
  }
  public ArrayList<Contato> SelectAllContato(Usuario user) throws FileNotFoundException, IOException{
        File file = new File("data_u"+codif.codifica(user.getNome())+".txt");
         if(!file.exists()){
            file.createNewFile();
        }
        FileReader f = new FileReader(file);
        BufferedReader lerArq = new BufferedReader(f);
        
        String linha = lerArq.readLine();

        ArrayList<Contato> contatos = new ArrayList<>();
        
        
        
        while(linha != null){
            Contato contato = ConverteStringParaContato(linha); 
            contatos.add(contato);
            linha = lerArq.readLine();
        }
        f.close();
        lerArq.close();
        return contatos;
  }
  
  public void AtualizaContatos(Usuario user, ArrayList<Contato> contatos) throws FileNotFoundException, IOException{
      if(contatos.size()==0){
        File file = new File("data_u"+codif.codifica(user.getNome())+".txt");
         if(!file.exists()){
            file.createNewFile();
        }
        
        FileWriter f = new FileWriter(file);
        PrintWriter grava = new PrintWriter(f);
        
        grava.print("");
        
        grava.close();
        f.close();
      }else{
          File file = new File("data_u"+codif.codifica(user.getNome())+".txt");
           if(!file.exists()){
            file.createNewFile();
        }
          FileWriter f = new FileWriter(file);
          PrintWriter grava = new PrintWriter(f);
          grava.print("");
          Collections.sort(contatos);
          contatos.forEach(c-> {
              try {
                  this.InsertContatoAtualiza(user,c.getNome(),c.getTelefone(),c.getEmail(),c.getEndereco());
              } catch (IOException ex) {
                  Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
              }
          });
          
          grava.close();
          f.close();
        
      }
             
        
  }

    public ArrayList<Contato> SelectContatoPorNomeAprx(Usuario user, String nome_buscar) throws FileNotFoundException, IOException {
        File file = new File("data_u"+codif.codifica(user.getNome())+".txt");
        FileReader f = new FileReader(file);
        BufferedReader lerArq = new BufferedReader(f);
        
        String linha = lerArq.readLine();
        
      
        ArrayList<Contato> contatos = new ArrayList<>();
        
        while(linha != null){
           Contato contato = ConverteStringParaContato(linha);
           if(contato.getNome().contains(nome_buscar)){
               contatos.add(contato);
           }
            linha = lerArq.readLine();
        }       
        f.close();
        lerArq.close();
        return contatos;
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
 
}
