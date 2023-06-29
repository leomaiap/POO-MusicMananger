package leomaia.musicmananger;

/**
 *
 * @author leoma
 */
public class Musica implements Comparable<Musica> {
    private int id;
    private String titulo;
    private int duracaoMin;
    private int duracaoSeg;
    private String autores;
    private int data;
    private String generoMusical;
//    private static int nMusicas = 0;
//    private static int nMusicaInstrumental = 0;
//    private static int nCancao = 0;

    public Musica(int id, String titulo, int duracaoMin, int duracaoSeg, String autores, int data, String generoMusiscal) {
        this.id = id;
        this.titulo = titulo;
        this.duracaoMin = duracaoMin;
        this.duracaoSeg = duracaoSeg;
        this.autores = autores;
        this.data = data;
        this.generoMusical = generoMusiscal;
    }
    
    public Musica(String titulo, int duracaoMin, int duracaoSeg, String autores, int data, String generoMusiscal) {
        this.titulo = titulo;
        this.duracaoMin = duracaoMin;
        this.duracaoSeg = duracaoSeg;
        this.autores = autores;
        this.data = data;
        this.generoMusical = generoMusiscal;
    }
    
    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracaoMin() {
        return duracaoMin;
    }

    public void setDuracaoMin(int duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    public int getDuracaoSeg() {
        return duracaoSeg;
    }

    public void setDuracaoSeg(int duracaoSeg) {
        this.duracaoSeg = duracaoSeg;
    }

    

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

//    public static int getnMusicas() {
//        return nMusicas;
//    }
//
//    public static void setnMusicas(int nMusicas) {
//        Musica.nMusicas = nMusicas;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public static int getnMusicaInstrumental() {
//        return nMusicaInstrumental;
//    }
//
//    public static void setnMusicaInstrumental(int nMusicaInstrumental) {
//        Musica.nMusicaInstrumental = nMusicaInstrumental;
//    }
//
//    public static int getnCancao() {
//        return nCancao;
//    }
//
//    public static void setnCancao(int nCancao) {
//        Musica.nCancao = nCancao;
//    }
    
    public String getDuracao() {
        String minutos = String.format("%02d", duracaoMin);
        String segundos = String.format("%02d", duracaoSeg);
        return minutos + ":" + segundos + " min";
    }
    
    public String getDataStr() {
        return String.valueOf(data);
    }

    @Override
    public String toString() {
        return titulo + " - " + autores;
    }

    @Override
    public int compareTo(Musica m) {
        return this.titulo.compareToIgnoreCase(m.getTitulo());
    }
    
    public String tipoMusica(){
        return "Musica";
    }
    
    public String letraCancao(){
        return "";
    }
    
}
