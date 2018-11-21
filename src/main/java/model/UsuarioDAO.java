/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import controller.codificador;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author brend
 */
public class UsuarioDAO {
    codificador codific = new codificador();

    public void InsertUsuario(Usuario user) throws IOException{
        File file = new File("users.txt");
        
        if(!file.exists()){
            file.createNewFile();
        }
   
        user.setNome(codific.codifica(user.getNome()));
        user.setSenha(codific.codifica(user.getSenha()));
        
        FileWriter f = new FileWriter(file,true);
        PrintWriter grava = new PrintWriter(f, true);
        
        grava.println(user.getNome()+";"+user.getSenha());
        
        File contatos = new File("data_u"+user.getNome()+".txt");
        contatos.createNewFile();
        
        f.close();
        grava.close();
    }
    
    public Usuario SelectUsuario(String n) throws FileNotFoundException, IOException{
        File file = new File("users.txt");
       if(!file.exists()){
            file.createNewFile();
        }
        FileReader f = new FileReader(file);
        BufferedReader lerArq = new BufferedReader(f);
        
        String linha = lerArq.readLine();
        
        while(linha != null){
            if(n.equals(codific.descodifica(linha.substring(0, linha.indexOf(";"))))){
                String nome = codific.descodifica(linha.substring(0,linha.indexOf(";")));
                String senha = codific.descodifica(linha.substring(linha.indexOf(";")+1, linha.length()));
                Usuario user = new Usuario(nome, senha);
                f.close();
                lerArq.close();
                return user;
        }else{
                linha = lerArq.readLine();
            }
        
    }
        f.close();
        lerArq.close();
        return null;
  }

    public ArrayList<Usuario> selectUsuarios() throws IOException {
        File file = new File("users.txt");
       if(!file.exists()){
            file.createNewFile();
        }
        FileReader f = new FileReader(file);
        BufferedReader lerArq = new BufferedReader(f);
        
        String linha = lerArq.readLine();
        ArrayList<Usuario> users = new ArrayList<Usuario>();
        while(linha != null){
                String nome = codific.descodifica(linha.substring(0,linha.indexOf(";")));
                String senha = codific.descodifica(linha.substring(linha.indexOf(";")+1, linha.length()));
                Usuario user = new Usuario(nome, senha);
                users.add(user);
                linha = lerArq.readLine();
         }
        f.close();
        lerArq.close();
        return users;
    }

	@SuppressWarnings("resource")
	public void delete(Usuario user) throws IOException {
		File file = new File("users.txt");
		 if(!file.exists()){
	            file.createNewFile();
	            
	        }
		
		 FileReader f = new FileReader(file);
	     BufferedReader lerArq = new BufferedReader(f);
		 String linha = lerArq.readLine();
		 ArrayList<Usuario> users = new ArrayList<>();
		 
		  while(linha != null){
			  Usuario veri = new Usuario(codific.descodifica(linha.substring(0,linha.indexOf(";"))), codific.descodifica(linha.substring(linha.indexOf(";")+1, linha.length())));
	            if(user.getNome().equals(veri.getNome()) && user.getSenha().equals(veri.getSenha())){
	            	continue; 
	               
	        }else{
	        		users.add(veri);
	                linha = lerArq.readLine();
	            }
	    }
		  	users.forEach(p->{
				try {
					InsertUsuario(p);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
	        f.close();
	        lerArq.close();
	  }
		 
		
}

