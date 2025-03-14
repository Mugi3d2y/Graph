public class Mention {

    private Artiste source;
    private Artiste destination;
    private int nombreMentions;

    public Mention(Artiste destination, Artiste source, int nombreMentions) {
        this.source = source;
        this.destination = destination;
        this.nombreMentions = nombreMentions;
    }


}
