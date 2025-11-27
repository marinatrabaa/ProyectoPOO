package Library.resources;

public class AudioBook extends Resource {

    private String duration;

    public AudioBook(String author, String duration, String id, String name){
        super(name, id, author);
        this.duration = duration;
    }

    @Override
    public void showDetails() {
        System.out.println("Author name: " + author + ", name: " + name + ", duration: " + this.duration);
    }
    
}
