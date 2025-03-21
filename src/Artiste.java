import java.util.Objects;

public class Artiste {

    private int id;
    private String nom;

    public int getId(){
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    private String categorie;

    public Artiste(int id, String nom, String categorie) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artiste artiste = (Artiste) o;
        return Objects.equals(nom, artiste.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    public String toString(){
        return this.nom + " (" + this.categorie +  ")";
    }
}
