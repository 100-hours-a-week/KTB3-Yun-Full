package domain;

public class EBooks extends Books {
    public String platform;
    public Boolean isAvailable;

    public EBooks(String title, String author, String genre, String publisher, int pages,
                  String platform, Boolean isAvailable) {
        super(title, author, genre, publisher, pages);
        this.platform = platform;
        this.isAvailable = isAvailable;
    }
}
