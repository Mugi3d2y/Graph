public class Mention {

    private Artiste source;
    private Artiste destination;
    private int nombreMentions;

    public Mention(Artiste source, Artiste destination, int nombreMentions) {
        this.source = source;
        this.destination = destination;
        this.nombreMentions = nombreMentions;
    }

    public Artiste getSource() {
        return source;
    }

    public Artiste getDestination() {
        return destination;
    }

    public int getNombreMentions() {
        return nombreMentions;
    }
}
