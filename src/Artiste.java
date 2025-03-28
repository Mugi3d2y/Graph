import java.util.Objects;

public class Artiste implements Comparable<Artiste>{

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

    private double cout;

    public Artiste(int id, String nom, String categorie) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.cout = Double.POSITIVE_INFINITY;

    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artiste artiste = (Artiste) o;
        return this.id == artiste.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String toString(){
        return this.nom + " (" + this.categorie +  ")";
    }

    public int compareTo(Artiste a){
        return Double.compare(this.cout, a.cout);
    }
}
