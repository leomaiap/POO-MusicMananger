package leomaia.musicmananger;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 *
 * @author leoma
 */
public class Usuario {
    private String nome;
    private int id;
    private String login;
    private String senha;
    private boolean admin;
    private String arqPlaylist;
    
    public Usuario(String nome, String login, String senha, boolean admin, String arqPlaylist) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.id = Objects.hash(nome + login);
        this.admin = admin;
        this.arqPlaylist = arqPlaylist;
    }

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.id = Objects.hash(nome+login);
        this.admin = false;
        this.arqPlaylist = login+".playlist";
    }
    
    public Usuario(String nome, String login, String senha, boolean admin) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.id = Objects.hash(nome+login);
        this.admin = true;
        this.arqPlaylist = login+".playlist";
    }
    
    public static Usuario carregarUsuario(String login){
        Usuario u;        
        u = PersistenciaUsuario.carregarUsuario(login);
        return u;
    }
    
    public static void removerUsuario(String login){
        PersistenciaUsuario.removerUsuario(login);
        Login.removerCadastro(login);
        apagarArquivoPlaylist(login);
    }
    
    public static void cadastroNovoUsuario(String nome, String login, String senha){
        Login.salvarCredenciais(login, senha, false);
        Usuario u = new Usuario(nome, login, senha);
        PersistenciaUsuario.salvarUsuario(u);
        criarArquivoPlaylist(login);
        //return u;
    }
    
    public static void cadastroNovoADM(String nome, String login, String senha){
        Login.salvarCredenciais(login, senha, true);
        Usuario u = new Usuario(nome, login, senha, true);
        PersistenciaUsuario.salvarUsuario(u);
        criarArquivoPlaylist(login);
        //return u;
    }
    
    public static void criarArquivoPlaylist(String login) {
        String nomeArquivo = login + ".playlist";
        File arquivo = new File(nomeArquivo);
        try {
            boolean criado = arquivo.createNewFile();
        } catch (IOException e) {
            System.out.println("Erro ao criar o arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }
    
    public static void apagarArquivoPlaylist(String login) {
        String nomeArquivo = login + ".playlist";
        File arquivo = new File(nomeArquivo);
        if (arquivo.exists()) {
            arquivo.delete();
        }
    }


    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public boolean isAdmin() {
        return admin;
    }
    
    public String getArqPlaylist() {
        return arqPlaylist;
    }

    @Override
    public String toString() {
        return "Usuario: (" + "nome: " + nome + " - login: " + login+")";
    }
    
    

    
}
