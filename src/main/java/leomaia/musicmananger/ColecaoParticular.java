/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package leomaia.musicmananger;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leoma
 */
public class ColecaoParticular extends ColecaoMusicasPrincipal{
    private List<Musica> colecaoParticular;

    public ColecaoParticular() {
        colecaoParticular = new ArrayList<>();
    }
    
    public void adicionarMusica(Musica m, String login) {
        if (colecaoParticular.contains(m)) {
            System.out.println("A música está presente na lista.");
        } else {
            colecaoParticular.add(m);
            PersistenciaParticular.salvarColecaoParticular(login, colecaoParticular);
        }
        
    }
    
    public void carregarParticular(String login, ColecaoMusicasPrincipal c){
        colecaoParticular = PersistenciaParticular.carregarColecaoParticular(login, c);
    }
    
    public void removerMusica(Musica m,String login) {
        colecaoParticular.remove(m);
        PersistenciaParticular.salvarColecaoParticular(login, colecaoParticular);
    }

//    public Musica buscarPorTituloParticular(String titulo, List<Musica>) {
//        for (Musica musica : colecaoParticular) {
//            if (musica.getTitulo().equalsIgnoreCase(titulo)) {
//                return musica;
//            }
//        }
//        return null; // Retorna null caso a música não seja encontrada na coleção particular
//    }

//    public void imprimirColecaoParticular() {
//        for (Musica musica : colecaoParticular) {
//            System.out.println("ID: " + musica.getId());
//            System.out.println("Título: " + musica.getTitulo());
//            System.out.println("Duração: " + musica.getDuracaoMin() + " min:" + musica.getDuracaoSeg() + " seg");
//            System.out.println("Autores: " + musica.getAutores());
//            System.out.println("Data: " + musica.getData());
//            System.out.println("Gênero: " + musica.getGeneroMusical());
//            if (musica instanceof Cancao cancao) {
//                System.out.println("Letra: " + cancao.getLetra().getTexto());
//                System.out.println("----------------------------------------");
//            } else if (musica instanceof MusicaInstrumental instrumental) {
//                System.out.println("Arquivo de Partitura: " + instrumental.getArqPartitura());
//                System.out.println("----------------------------------------");
//            }
//        }
//    }

    @Override
    public List<Musica> getMusicas() {
        return colecaoParticular;
    }
}
