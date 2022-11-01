package classes;

import java.util.ArrayList;

public class Librarie {
    ArrayList<Carte> carti = new ArrayList<Carte>();
    private final int PRETMAXIMCARTE;

    // constructor
    public Librarie(int p) {
        PRETMAXIMCARTE = p;
    }

    // public Librarie() {
    //
    // }

    // adauga o carte in librarie
    // in: un obiect de tip carte, care va fi adaugat in lista de carti
    // out: no output, e constructor
    public void adaugaCarte(Carte c) {
        if (!(c.getPret() >= PRETMAXIMCARTE))
            carti.add(c);
    }

    // adauga creeaza un obiect de tip carte si il adauga in librarie
    // in: String titlu carte, String autor carte, int pret carte
    // out: no output, e constructor
    public void adaugaCarte(String titlu, String autor, int pret) {
        if (!(pret >= PRETMAXIMCARTE)) {
            Carte c = new Carte(titlu, autor, pret);
            carti.add(c);
        }
    }

    public int getPretMaximCarte() {
        return PRETMAXIMCARTE;
    }

    // metoda get pentru numarul de carti din librarie
    public int getNrCarti() {
        return carti.size();
    }

    // cauta cartea cu pretul minim si o returneaza
    // in: no input
    // output: un obiect de tip carte sau null daca nu sunt carti
    public Carte getCartePretMinim() {
        if (carti.size() > 0) {
            int min = 9999999;
            int poz = -1;
            for (int i = 0; i < carti.size(); i++) {
                if (min > carti.get(i).getPret()) {
                    min = carti.get(i).getPret();
                    poz = i;
                }
            }
            return (carti.get(poz));
        } else
            return null;
    }

    // returneaza cartea la indexul dat
    // in: int index
    // out: carte la index
    public Carte getCarteLaIndex(int index) {
        return carti.get(index);
    }

    // cauta cartea cu pretul maxim si o returneaza
    // in: no input
    // output: un obiect de tip carte sau null daca nu sunt carti
    public Carte getCartePretMaxim() {
        if (carti.size() > 0) {
            int max = -1;
            int poz = -1;
            for (int i = 0; i > carti.size(); i++) {
                if (max > carti.get(i).getPret()) {
                    max = carti.get(i).getPret();
                    poz = i;
                }
            }
            return (carti.get(poz));
        } else
            return null;
    }

    // calculeaza media preturilor din librarie
    // in: no input
    // output: int medie preturi sau -1 daca nu sunt carti
    public int mediePreturiCarti() {
        if (carti.size() > 0) {
            int sum = 0;
            for (int i = 0; i < carti.size(); i++) {
                sum += carti.get(i).getPret();
            }
            return (sum / carti.size());
        } else
            return -1;
    }

    // metoda to string, returneaza toate detaliile cartilor intr-un format ordonat
    // sau un mesaj cum nu sunt carti, in cazul in care libraria nu are carti
    public String toString() {
        if (carti.size() > 0) {
            StringBuffer sb = new StringBuffer("Libraria are urmatoarele carti: \n");
            for (int i = 0; i < carti.size(); i++) {
                sb.append(carti.get(i) + "\n");
            }
            return (sb.toString());
        } else
            return ("Libraria nu are carti");
    }

}