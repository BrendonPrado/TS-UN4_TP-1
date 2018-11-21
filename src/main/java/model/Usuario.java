/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Usuario  implements logar{
    private String nome;
    private String senha;
    private boolean logado;

    public Usuario() {
        this.nome = null;
        this.senha= null;
        this.logado = false;
    }

    public boolean isLogado() {
        return logado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }
    
    @Override
    public boolean logar_senha(String senha_tentativa) {
         if(this.senha.equals(senha_tentativa)){
             this.logado = true;
             return true;
         }
         return false;
    }
}
