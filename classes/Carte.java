package classes;

public class Carte {
    private String titlu;
    private String autor;
    private int pret;

    // constructor,
    // in: String tilu carte, String autor carte si int pret carte
    // out: no output, e constructor
    public Carte(String titlu, String autor, int pret) {
        this.titlu = titlu;
        this.autor = autor;
        this.pret = pret;
    }

    public Carte() {
        titlu = "FARA TITLU";
        autor = "FARA AUTOR";
        pret = 0;
    }

    // metoda get pentru titlu carte
    public String getTitlu() {
        return titlu;
    }

    // metoda set pentru titlu carte
    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    // metoda get pentru autorul cartii
    public String getAutor() {
        return autor;
    }

    // metoda set pentru autorul cartii
    public void setAutor(String autor) {
        this.autor = autor;
    }

    // metoda get pentru pretul cartii
    public int getPret() {
        return pret;
    }

    // metoda set pentru pretul cartii
    public void setPret(int pret) {
        this.pret = pret;
    }

    // metoda to string, returneaza campurile cartii intr-un format ordonat
    public String toString() {
        return (titlu + " " + autor + " " + pret);
    }
}