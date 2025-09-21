package domain;

/*
기존의 RealBook이라는 클래스명이 직관적이지 못하고 혼동을 불러올 수 있을 것 같아
EBookImpl로 바꾸었습니다. 하지만 이것도 적절한 네이밍인지는 고민의 여지가 있습니다. (09.18)
 */
public class EBookImpl extends EBooks {
    public EBookImpl(String title, String author, String genre, String publisher, int pages,
                     String platform, Boolean isAvailable) {
        super(title, author, genre, publisher, pages, platform, isAvailable);
    }
}
