import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph{
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
                        Artiste artiste = new Artiste(id, nom, categorie);
                        liensDArtistes.put(artiste, new HashSet<>());
                        listeArtistes.put(id,artiste);
                        nomsArtistes.put(nom,artiste);

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            try (BufferedReader br = new BufferedReader(new FileReader(mentionFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        int idSource = Integer.parseInt(parts[0].trim());
                        int idDestination = Integer.parseInt(parts[1].trim());
                        int nbMentions = Integer.parseInt(parts[2].trim());


                        Artiste source = getArtisteById(idSource);
                        Artiste destination = getArtisteById(idDestination);

                        if (source != null && destination != null) {
                            Mention mention = new Mention(source, destination, nbMentions);
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

    // Complexité: O(1)
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
                Artiste voisin = mention.getDestination();
                if(!sommetsVisites.contains(voisin)){
                    sommetsVisites.add(voisin);
                    cheminInverseArtistes.put(voisin,mention);
                    if(voisin.equals(artisteDestination)){
                        List<String> cheminOutput = new ArrayList<>();
                        Artiste artisteActuel = artisteDestination;
                        double cout = 0;
                        while(!artisteActuel.equals(artisteSource)){
                            Mention m = cheminInverseArtistes.get(artisteActuel);
                            cout += 1.0/m.getNombreMentions();
                            cheminOutput.addFirst(artisteActuel.toString());
                            artisteActuel = m.getSource();
                        }

                        cheminOutput.addFirst(artisteSource.toString());

                        System.out.println("Longueur du chemin : " + (cheminOutput.size()-1 ));
                        System.out.println("Coût total du chemin : " + cout);
                        System.out.println("Chemin : \n");
                        for (String s : cheminOutput) {
                            System.out.println(s+ "\r" );
                        }
                        return;
                    }
                    artistesArrayDeque.add(voisin);
                }
            }
        }
        throw new IllegalArgumentException("Aucun chemin entre " + a1 + " et " + a2);
    }


    public void trouverCheminMaxMentions(String a1, String a2){
        Artiste source = getArtistByName(a1.trim());
        Artiste destination = getArtistByName(a2.trim());
        if(source == null || destination == null) throw new IllegalArgumentException("Les artistes : " + a1 + " ou " + a2 + " n'existent pas.");

        PriorityQueue<Artiste> mentionArtistes = new PriorityQueue<>();
        HashMap<Artiste, Mention> cheminInverse = new HashMap<>();


        source.setCout(0);
        mentionArtistes.add(source);
        while (!mentionArtistes.isEmpty()){
            Artiste a = mentionArtistes.poll();
            for (Mention m : arcsSortants(a)){
                Artiste voisin = m.getDestination();
                double nouveauCout = a.getCout() + (1.0/m.getNombreMentions());
                if(nouveauCout<voisin.getCout()){
                    voisin.setCout(nouveauCout);
                    cheminInverse.put(voisin,m);
                    mentionArtistes.add(voisin);
                }
            }
        }
        if(!cheminInverse.containsKey(destination)) throw new IllegalArgumentException("Aucun chemin entre " + a1 + " et " + a2);

        LinkedList<String> affichage = new LinkedList<>();
        Artiste actuel = destination;
        while (!actuel.equals(source)){
            affichage.addFirst(actuel.toString());
            actuel = cheminInverse.get(actuel).getSource();
        }
        affichage.addFirst(source.toString());

        System.out.println("Longueur du chemin : " + (affichage.size() - 1));
        System.out.println("Cout total du chemin : " + destination.getCout());
        System.out.println("Chemin : \n");
        for (String s : affichage){
            System.out.println(s + "\r");
        }
        return;
    }
}
