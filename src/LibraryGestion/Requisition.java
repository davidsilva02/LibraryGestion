package LibraryGestion;


public class Requisition {
    private final Book book;
    private final Reader reader;
    private final Date reqDate;
    private final Date devDate;

    public Requisition(Book book, Reader reader, Date reqDate, Date devDate) {
        this.book = book;
        this.reader = reader;
        this.reqDate =reqDate;
        this.devDate =devDate;
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public Date getReqDate() {
        return reqDate;
    }

    public Date getDevDate() {
        return devDate;
    }

    @Override
    public String toString() {
        return "------------------------------------------------------Requisition"+"\n"+
                //print date with format day/month/year
                "Requisition Date "+ reqDate +"\n"+
                "Devolucion Date "+devDate+"\n"+
                reader+"\n"+
                book+"\n";
    }
}
