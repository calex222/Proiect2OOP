package classes;

abstract class LibrarieModel {

    protected final int PRETMAXIMCARTE;

    public LibrarieModel(int p) {
        PRETMAXIMCARTE = p;
    }

    public abstract void adaugaCarte(Carte c);

    public abstract void adaugaCarte(String a, String b, int c);
}
