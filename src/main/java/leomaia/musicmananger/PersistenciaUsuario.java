/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package leomaia.musicmananger;

import java.io.*;
import javax.swing.DefaultListModel;

/**
 *
 * @author leoma
 */
public class PersistenciaUsuario {
    private static final String ARQUIVO_USUARIOS = "usuarios.bin";
    
    public static void salvarUsuario(Usuario usuario) {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(ARQUIVO_USUARIOS, true))) {
            outputStream.writeUTF(usuario.getNome());
            outputStream.writeInt(usuario.getId());
            outputStream.writeUTF(usuario.getLogin());
            // Não salva a senha no arquivo
            outputStream.writeBoolean(usuario.isAdmin());
            outputStream.writeUTF(usuario.getArqPlaylist());
        } catch (IOException e) {
            e.printStackTrace();
            // Tratar a exceção conforme sua necessidade
        }
    }

    public static Usuario carregarUsuario(String login) {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(ARQUIVO_USUARIOS))) {
            while (inputStream.available() > 0) {
                String nome = inputStream.readUTF();
                int id = inputStream.readInt();
                String usuarioLogin = inputStream.readUTF();
                boolean isAdmin = inputStream.readBoolean();
                String arqPlaylist = inputStream.readUTF();
                if (usuarioLogin.equals(login)) {
                    // Encontrou o usuário desejado
                    return new Usuario(nome, usuarioLogin, "", isAdmin, arqPlaylist);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Tratar a exceção conforme sua necessidade
        }
        return null; // Retorna null caso não encontre o usuário com o login especificado
    }

    public static void removerUsuario(String login) {
        try {
            File inputFile = new File(ARQUIVO_USUARIOS);
            File tempFile = new File("temp.enc");

            try (DataInputStream input = new DataInputStream(new FileInputStream(inputFile));
                 DataOutputStream output = new DataOutputStream(new FileOutputStream(tempFile))) {

                while (input.available() > 0) {
                    String nome = input.readUTF();
                    int id = input.readInt();
                    String usuarioLogin = input.readUTF();
                    boolean isAdmin = input.readBoolean();
                    String arqPlaylist = input.readUTF();

                    if (!usuarioLogin.equals(login)) {
                        output.writeUTF(nome);
                        output.writeInt(id);
                        output.writeUTF(usuarioLogin);
                        output.writeBoolean(isAdmin);
                        output.writeUTF(arqPlaylist);
                    }
                }
            }

            // Remove o arquivo original
            if (inputFile.delete()) {
                // Renomeia o arquivo temporário para o nome original
                if (!tempFile.renameTo(inputFile)) {
                    System.out.println("Erro ao renomear o arquivo temporário");
                }
            } else {
                System.out.println("Erro ao remover o arquivo original");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    public static void imprimirUsuarios() {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(ARQUIVO_USUARIOS))) {
            while (inputStream.available() > 0) {
                String nome = inputStream.readUTF();
                int id = inputStream.readInt();
                String login = inputStream.readUTF();
                boolean isAdmin = inputStream.readBoolean();
                String arqPlaylist = inputStream.readUTF();

                System.out.println("Nome: " + nome);
                System.out.println("ID: " + id);
                System.out.println("Login: " + login);
                System.out.println("Admin: " + isAdmin);
                System.out.println("Arquivo de Playlist: " + arqPlaylist);
                System.out.println("----------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Tratar a exceção conforme sua necessidade
        }
    }

    public static DefaultListModel<Usuario> usuarioToListModel() {
        DefaultListModel<Usuario> DL = new DefaultListModel<>();
        Usuario u;
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(ARQUIVO_USUARIOS))) {
            while (inputStream.available() > 0) {
                String nome = inputStream.readUTF();
                int id = inputStream.readInt();
                String usuarioLogin = inputStream.readUTF();
                boolean isAdmin = inputStream.readBoolean();
                String arqPlaylist = inputStream.readUTF();
                    // Encontrou o usuário desejado
                u = new Usuario(nome, usuarioLogin, "", isAdmin, arqPlaylist);
                DL.addElement(u);

            }
        } catch (IOException e) {
            e.printStackTrace();
            // Tratar a exceção conforme sua necessidade
        }
        return DL;
    }
}
