package leomaia.musicmananger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 *
 * @author leoma
 */
public class Letra {
    private String nomeArqLetra;
    private String texto;
    
    public Letra(String nomeArqLetra) {
        this.nomeArqLetra = nomeArqLetra;
        lerArquivo();
    }
    
    private void lerArquivo() {
        StringBuilder sb = new StringBuilder();
        String caminhoDiretorio = "letras";
        String caminhoArquivo = caminhoDiretorio + "/" + nomeArqLetra;

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        texto = sb.toString();
    }

    public String getNomeArqLetra() {
        return nomeArqLetra;
    }   
    
    public String getTexto() {
        return texto;
    }

}
