public class Books {
    String title;
    String author;
    String genre;
    String publisher;
    int pages;

    public Books(String title, String author, String genre, String publisher, int pages) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.pages = pages;
    }
}

class EBooks extends Books {
    String platform;
    Boolean isAvailable;

    public EBooks(String title, String author, String genre, String publisher, int pages,
                  String platform, Boolean isAvailable) {
        super(title, author, genre, publisher, pages);
        this.platform = platform;
        this.isAvailable = isAvailable;
    }
}


class EBookImpl extends EBooks {
    public EBookImpl(String title, String author, String genre, String publisher, int pages,
                     String platform, Boolean isAvailable) {
        super(title, author, genre, publisher, pages, platform, isAvailable);
    }
}
