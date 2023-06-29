package leomaia.musicmananger;

import java.util.Objects;

/**
 *
 * @author leoma
 */
public class MusicaInstrumental extends Musica{
    private String arqPartitura;

    public MusicaInstrumental(String arqPartitura, int id, String titulo, int duracaoMin, int duracaoSeg, String autores, int data, String generoMusiscal) {
        super(id, titulo, duracaoMin, duracaoSeg, autores, data, generoMusiscal);
        this.arqPartitura = arqPartitura;
//        Musica.setnMusicaInstrumental(getnMusicaInstrumental()+1);
//        Musica.setnMusicas(getnMusicas()+1);
    }

    public MusicaInstrumental(String arqPartitura, String titulo, int duracaoMin, int duracaoSeg, String autores, int data, String generoMusiscal) {
        super(titulo, duracaoMin, duracaoSeg, autores, data, generoMusiscal);
        this.arqPartitura = arqPartitura;
        int hashCode = Objects.hash(titulo+autores);
        super.setId(Math.abs(hashCode));
//        Musica.setnMusicaInstrumental(getnMusicaInstrumental()+1);
//        Musica.setnMusicas(getnMusicas()+1);
        //colocar na colecao
    }

    public String getArqPartitura() {
        return arqPartitura;
    }

    @Override
    public String toString() {
        return "♫ "+ super.toString();
    }
    
    @Override
    public String tipoMusica(){
        return "Musica Instrumental";
    }
    
    @Override
    public String letraCancao(){
        return "Não Possui letra\nMusica Instrumental";
    }
    
}
