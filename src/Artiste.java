import java.util.Objects;

public class Artiste {
    private String nom;
    private String categorie;

    public Artiste(String nom, String categorie) {
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
