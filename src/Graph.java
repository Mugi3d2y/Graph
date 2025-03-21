import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Map<Artiste, Set<Mention>> liensDArtistes;

    public Graph(String artisteFile, String mentionFile){
        super();
        liensDArtistes = new HashMap<Artiste, Set<Mention>>();

    }


    protected void ajouterSommet(Artiste a){
        Set<Mention> listeMentions = new HashSet<Mention>();
        liensDArtistes.put(a,listeMentions);
    }

    protected void ajouterArc(Mention m){
        Artiste a = m.getSource();
        liensDArtistes.get(a).add(m);
    }

    // Complexité: ?
    public Set<Mention> arcsSortants(Artiste a) {
        //à compléter
        return liensDArtistes.get(a);
    }

    public String trouverCheminLePlusCourt(String a1, String a2){
        return null;
    }


    public String trouverCheminMaxMentions(String a1, String a2){
        return null;
    }
}
