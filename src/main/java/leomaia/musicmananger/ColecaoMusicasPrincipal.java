package leomaia.musicmananger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 *
 * @author leoma
 */
public class ColecaoMusicasPrincipal {
    private List<Musica> colecao;

    public ColecaoMusicasPrincipal() {
        colecao = new ArrayList<>();
    }
    
    public static DefaultListModel<Musica> colecaoToListmodel(List<Musica> c){
        DefaultListModel<Musica> DLM = new DefaultListModel<>();
        for(Musica musica : c){
            DLM.addElement(musica);
        }
        return DLM;
    }

    public void adicionarMusica(Musica musica) {
        colecao.add(musica);
        Collections.sort(colecao);
        PersistenciaMusica.salvarColecao(colecao);
    }

    public void removerMusica(Musica m, List<Musica> c) {
        colecao.remove(m);
        PersistenciaMusica.salvarColecao(colecao);
    }
    
//    public Musica buscarPorTitulo(String titulo, List<Musica> c) {
//        System.out.println("Fazendo Busca");
//        for (Musica musica : c) {
//            String titulo1 = musica.getTitulo();
//            //System.out.println(titulo1);
//            if (musica.getTitulo().equalsIgnoreCase(titulo)) {
//                //System.out.println("Musica encontrada");
//                return musica;
//            }
//        }
//        //System.out.println("Nao encontrada "+titulo);
//        return null;
//    }
    
    public Musica buscarPorId(int id) {
        for (Musica musica : colecao) {
            if (musica.getId() == id) {
                return musica;
            }
        }
        return null;
    }
    
    public void carregarColecao(){
        PersistenciaMusica.carregarColecao(colecao);
    }
    
    public List<Musica> getMusicas() {
        return colecao;
    }
}
