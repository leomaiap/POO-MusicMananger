/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package leomaia.musicmananger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leoma
 */
public class PersistenciaParticular {
    
    public static void salvarColecaoParticular(String login, List<Musica> colecaoParticular) {
        String nomeArquivo = login + ".playlist";

        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(nomeArquivo))) {
            for (Musica musica : colecaoParticular) {
                outputStream.writeInt(musica.getId());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar a coleção particular: " + e.getMessage());
        }
    }

    public static List<Musica> carregarColecaoParticular(String login, ColecaoMusicasPrincipal colecaoPrincipal) {
        String nomeArquivo = login + ".playlist";
        List<Musica> musicasParticulares = new ArrayList<>();

        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(nomeArquivo))) {
            while (inputStream.available() > 0) {
                int id = inputStream.readInt();
                Musica musica = colecaoPrincipal.buscarPorId(id);
                if (musica != null) {
                    musicasParticulares.add(musica);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar a coleção particular: " + e.getMessage());
        }

        return musicasParticulares;
    }
    
}
