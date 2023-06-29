package leomaia.musicmananger;

import java.io.*;
import java.util.List;

/**
 *
 * @author leoma
 */
public class PersistenciaMusica {
    private static final String NOME_ARQUIVO = "bibliotecaMusical.bin";
     
    //SALVAR
    public static void salvarColecao(List<Musica> colecao) {
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            for (Musica musica : colecao) {
                //if (musica instanceof Cancao cancao) {
                if (musica.getClass().equals(Cancao.class)){
                    outputStream.writeInt(0); // Identificador de Cancao
                    salvarCancao((Cancao) musica, outputStream);
                } else{
                    outputStream.writeInt(1); // Identificador de MusicaInstrumental
                    salvarMusicaInstrumental((MusicaInstrumental) musica, outputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void salvarCancao(Cancao cancao, DataOutputStream outputStream) throws IOException {
        // Escrever os atributos da Cancao no arquivo binário
        outputStream.writeInt(cancao.getId());
        outputStream.writeUTF(cancao.getTitulo());
        outputStream.writeInt(cancao.getDuracaoMin());
        outputStream.writeInt(cancao.getDuracaoSeg());
        outputStream.writeUTF(cancao.getAutores());
        outputStream.writeInt(cancao.getData());
        outputStream.writeUTF(cancao.getGeneroMusical());
        outputStream.writeUTF(cancao.getNomeArqLetra()); // Escrever o nome do arquivo de letra no arquivo
        System.out.println("Salvo no arquivo");
    }

    private static void salvarMusicaInstrumental(MusicaInstrumental musicaInstrumental, DataOutputStream outputStream) throws IOException {
        // Escrever os atributos da MusicaInstrumental no arquivo binário
        outputStream.writeInt(musicaInstrumental.getId());
        outputStream.writeUTF(musicaInstrumental.getTitulo());
        outputStream.writeInt(musicaInstrumental.getDuracaoMin());
        outputStream.writeInt(musicaInstrumental.getDuracaoSeg());
        outputStream.writeUTF(musicaInstrumental.getAutores());
        outputStream.writeInt(musicaInstrumental.getData());
        outputStream.writeUTF(musicaInstrumental.getGeneroMusical());
        outputStream.writeUTF(musicaInstrumental.getArqPartitura()); // Escrever o nome do arquivo de partitura no arquivo
    }


    
    //CARREGAR
    public static void carregarColecao(List<Musica> colecao) {
        try (DataInputStream inputStream = new DataInputStream(new FileInputStream(NOME_ARQUIVO))) {
            colecao.clear();
            while (inputStream.available() > 0) {
                int tipo = inputStream.readInt();
                if (tipo == 0) {
                    Cancao cancao = carregarCancao(inputStream);
                    colecao.add(cancao);
                } else if (tipo == 1) {
                    MusicaInstrumental musicaInstrumental = carregarMusicaInstrumental(inputStream);
                    colecao.add(musicaInstrumental);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static Cancao carregarCancao(DataInputStream inputStream) throws IOException {
        // Ler os atributos da Cancao do arquivo binário
        int id = inputStream.readInt();
        String titulo = inputStream.readUTF();
        int duracaoMin = inputStream.readInt();
        int duracaoSeg = inputStream.readInt();
        String autores = inputStream.readUTF();
        int data = inputStream.readInt();
        String generoMusiscal = inputStream.readUTF();
        Letra letra = new Letra(inputStream.readUTF()); // Ler o nome do arquivo de letra do arquivo

        // Construir e retornar a instância de Cancao
        return new Cancao(id, titulo, duracaoMin, duracaoSeg, autores,data, generoMusiscal, letra);
    }

    private static MusicaInstrumental carregarMusicaInstrumental(DataInputStream inputStream) throws IOException {
        // Ler os atributos da MusicaInstrumental do arquivo binário
        int id = inputStream.readInt();
        String titulo = inputStream.readUTF();
        int duracaoMin = inputStream.readInt();
        int duracaoSeg = inputStream.readInt();
        String autores = inputStream.readUTF();
        int data = inputStream.readInt();
        String generoMusiscal = inputStream.readUTF();
        String arqPartitura = inputStream.readUTF(); // Ler o nome do arquivo de partitura do arquivo

        // Construir e retornar a instância de MusicaInstrumental
        return new MusicaInstrumental(arqPartitura, id, titulo, duracaoMin, duracaoSeg, autores, data, generoMusiscal);
    } 
}
