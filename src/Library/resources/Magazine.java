package Library.resources;

public class Magazine extends Resource {

    private String edition;

    public Magazine(String name, String id, String author, String edition){
        super(name, id, author);
        this.edition = edition;
    }

    @Override
    public void showDetails() {
        System.out.println("Author name: " + author + ", edition: " + edition);
    }
    
}
