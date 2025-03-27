import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {
    private Map<Artiste, Set<Mention>> liensDArtistes;
    private HashMap<Integer,Artiste> listeArtistes;
    private HashMap<String, Artiste> nomsArtistes;



    public Graph(String artisteFile, String mentionFile){
        super();
        liensDArtistes = new HashMap<Artiste, Set<Mention>>();
        listeArtistes = new HashMap<Integer, Artiste>();
        nomsArtistes = new HashMap<String, Artiste>();


            // Lire les artistes et les ajouter au graphe
            try (BufferedReader br = new BufferedReader(new FileReader(artisteFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0].trim());
                        String nom = parts[1].trim();
                        String categorie = parts[2].trim();
                        Artiste artiste = new Artiste(id, nom, categorie); // Constructeur Artiste(nom, categorie)
                        liensDArtistes.put(artiste, new HashSet<>());// Initialiser l'ensemble de mentions
                        listeArtistes.put(id,artiste);
                        nomsArtistes.put(nom,artiste);

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Lire les mentions et ajouter les relations
            try (BufferedReader br = new BufferedReader(new FileReader(mentionFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        int idSource = Integer.parseInt(parts[0].trim());
                        int idDestination = Integer.parseInt(parts[1].trim());
                        int nbMentions = Integer.parseInt(parts[2].trim());

                        // Trouver les artistes correspondants
                        Artiste source = getArtisteById(idSource);
                        Artiste destination = getArtisteById(idDestination);

                        if (source != null && destination != null) {
                            Mention mention = new Mention(source, destination, nbMentions); // Nouveau constructeur
                            liensDArtistes.get(source).add(mention);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }


    protected Artiste getArtisteById(int id){
        return listeArtistes.get(id);
    }

    protected Artiste getArtistByName(String nom){
        return nomsArtistes.get(nom);
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

    public void trouverCheminLePlusCourt(String a1, String a2){
        Artiste artisteSource = getArtistByName(a1.trim());
        Artiste artisteDestination = getArtistByName(a2.trim());
        if(artisteDestination == null || artisteSource == null)
            throw new IllegalArgumentException("Un des artistes n'existe pas : " + a1 + " ou " + a2);

        ArrayDeque<Artiste> artistesArrayDeque = new ArrayDeque<>();
        HashMap<Artiste, Mention> cheminInverseArtistes = new HashMap<>();
        Set<Artiste> sommetsVisites = new HashSet<>();

        artistesArrayDeque.add(artisteSource);
        sommetsVisites.add(artisteSource);
        while(!artistesArrayDeque.isEmpty()){
            Artiste artisteCourant = artistesArrayDeque.poll();
            for(Mention mention : arcsSortants(artisteCourant)){
                Artiste artisteVoisin = mention.getDestination();
                if(!sommetsVisites.contains(artisteVoisin)){
                    sommetsVisites.add(artisteVoisin);
                    cheminInverseArtistes.put(artisteVoisin,mention);
                    if(artisteVoisin.equals(artisteDestination)){
                        List<String> cheminOutput = new ArrayList<>();
                        Artiste artisteActuel = artisteDestination;
                        while(!artisteActuel.equals(artisteSource)){
                            Mention m = cheminInverseArtistes.get(artisteActuel);
                            cheminOutput.add(0,artisteActuel.toString());
                            artisteActuel = m.getSource();
                        }

                        cheminOutput.add(0,artisteSource.toString());

                        System.out.println("Longueur du chemin : " + (cheminOutput.size()-1 ));
                        for (String s : cheminOutput) {
                            System.out.println(s+ "\n" );
                        }
                        return;
                    }
                    artistesArrayDeque.add(artisteVoisin);
                }
            }
        }
        throw new IllegalArgumentException("Aucun chemin entre " + a1 + " et " + a2);
    }


    public List<String> trouverCheminMaxMentions(String a1, String a2){
        return null;
    }
}
