/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Aluno
 */
public class Contato implements Comparable<Contato> {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;

    public Contato(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }
    public Contato(){
        this.nome = null;
        this.telefone = null;
        this.email = null;
        this.endereco = null;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public int compareTo(Contato contato) {
        String a = this.getNome().toLowerCase();
        String b = contato.getNome().toLowerCase();
        return a.compareTo(b);
}
    

    
}
