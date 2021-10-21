package LibraryGestion;

public class Book {

    //attributes of the book
    private final String authorName;
    private final String titleBook;
    private boolean available;

    /**
     * Create new book with author name and title book, and define available=true ,book is available for
     * requisition
     * @param authorName author name
     * @param titleBook title book
     * @param available boolean available
     */
    public Book(String authorName, String titleBook,boolean available) {
        this.authorName = authorName;
        this.titleBook = titleBook;
        this.available=available;
    }

    public String getTitleBook() {
        return titleBook;
    }

    /**
     * Add requisition for book
     * @param reader object with reader name and reader number
     * @param reqDate date of requisition
     * @param devDate date of devolution
     * @return new requisition
     */
    public Requisition requisitionBook (Reader reader, Date reqDate, Date devDate){
        Book book=new Book(this.authorName,this.titleBook,this.available);
        return new Requisition(book,reader,reqDate,devDate);
    }

    public boolean setAvailable() {
        return available;
    }
    public void setAvailable(boolean available){
        this.available=available;
    }

    @Override
    public String toString() {
        return  "------------------------------------"+"Book"+"\n"+
                "Title: "+titleBook+"\n"+
                "Author: "+authorName;
    }
}
