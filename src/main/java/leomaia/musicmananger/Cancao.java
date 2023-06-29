package leomaia.musicmananger;

import java.util.Objects;

/**
 *
 * @author leoma
 */
public class Cancao extends Musica{
    private Letra letra;
    //private String arquivoLetra;

    public Cancao(int id, String titulo, int duracaoMin, int duracaoSeg, String autores, int data, String generoMusiscal, Letra letra) {
        super(id, titulo, duracaoMin, duracaoSeg, autores, data, generoMusiscal);
        this.letra = letra;
//        Musica.setnCancao(getnCancao()+1);
//        Musica.setnMusicas(getnMusicas()+1);
    }

    public Cancao(String titulo, int duracaoMin, int duracaoSeg, String autores, int data, String generoMusiscal) {
        super(titulo, duracaoMin, duracaoSeg, autores, data, generoMusiscal);
        int hashCode = Objects.hash(titulo+autores);
        super.setId(Math.abs(hashCode));
//        Musica.setnCancao(getnCancao()+1);
//        Musica.setnMusicas(getnMusicas()+1);
        String arquivoLetra = titulo+".txt";
        letra = new Letra(arquivoLetra);
        //Ler letra
        //colocar na colecao
    }

    public Letra getLetra() {
        return letra;
    }  
    
    public String getNomeArqLetra(){
       return letra.getNomeArqLetra();
    }
    
    @Override
    public String toString() {
        return "🎧 "+ super.toString();
    }
    
    @Override
    public String tipoMusica(){
        return "Canção";
    }
    
    @Override
    public String letraCancao(){
        return letra.getTexto();
    }
}
