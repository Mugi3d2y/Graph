import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListeDAdjacence extends Graph {

    private Map<Artiste, Set<Mention>> liensDArtistes;

    public ListeDAdjacence(){
        super();
        liensDArtistes = new HashMap<Artiste, Set<Mention>>();
    }


    protected void ajouterSommet(Mention m){
        return;
    }

    protected void ajouterArc(Artiste a){
        return;
    }

    // Complexité: ?
    public Set<Mention> arcsSortants(Artiste a) {
        //à compléter
        return null;
    }

    // Complexité: ?
    public boolean sontAdjacents(Artiste a1, Artiste a2) {
        // à compléter
        return false;
    }

}
