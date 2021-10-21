package LibraryGestion;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryGestion {
    ArrayList<Book> books;
    ArrayList<Requisition> requisitions;
    ArrayList<Reader> readers;
    Scanner sc;

    public static void main(String[] args) {
        LibraryGestion LibraryGestion = new LibraryGestion();
        LibraryGestion.start();
    }


    public LibraryGestion() {
        books = new ArrayList<>();
        requisitions = new ArrayList<>();
        readers = new ArrayList<>();

        sc = new Scanner(System.in);
    }

    private void start() {
        //add books
        addBook("Eca Queiroz", "Os Maias");
        addBook("Camilo Castelo Branco", "Amor de Perdicao");

        //add readers
        addReader("David", 124);
        addReader("Joao", 125);

        //menu
        int option;

        do {
            System.out.println("""
                    Enter option:
                    1--->Add book
                    2--->All books of library
                    3--->All available books of library
                    4--->Add reader
                    5--->Add requisition
                    6--->Search requisitions with certain date
                    7--->All requisitions
                    8--->All readers
                    9--->Requisitions of reader
                    10--->Delivery Book
                    0--->EXIT
                    """);

            option = insertIntNumber(sc);

            if (option == 1) {
                System.out.println("Add book in library");

                System.out.println("Enter title of book:");
                String titleBook = sc.nextLine();
                titleBook += sc.nextLine();

                System.out.println("Enter author of book:");
                String authorBook = sc.nextLine();

                addBook(authorBook, titleBook);
            }

            if (option == 2) {
                getAllBooks();
            }

            if (option == 3) {
                getAllAvailableBooks();
            }

            if (option == 4) {
                System.out.println("Enter reader name:");
                String readerName = sc.nextLine();
                readerName += sc.nextLine();

                System.out.println("Enter reader number:");
                int readerNumber = insertIntNumber(sc);

                addReader(readerName, readerNumber);
            }

            if (option == 5) {
                System.out.println("Add requisition");

                System.out.println("Enter title of book");
                String titleBook = sc.nextLine();
                titleBook += sc.nextLine();

                //print all readers with index for reader
                getAllReaders();

                System.out.println("Choose the reader");
                int readerIndex = insertIntNumber(sc);

                System.out.println("Enter requisition date");
                Date dateReq= enterDate(sc);

                System.out.println("Enter devolucion date");
                Date dateDev = enterDate(sc);

                newRequesition(titleBook, readerIndex, dateReq, dateDev);
            }

            if (option == 6) {
                System.out.println("Enter the date to search");
                Date date = enterDate(sc);

                searchRequesitionDate(date);
            }

            if (option == 7) {
                getAllRequisitions();
            }

            if (option == 8) {
                getAllReaders();
            }

            if (option == 9) {
                getAllReaders();

                System.out.println("Choose the reader");
                int readerIndex = insertIntNumber(sc);

                Reader reader= readers.get(readerIndex);

                getAllRequisitionsReader(reader);
            }

            if (option==10){
                System.out.println("Book delivery");

                System.out.println("Enter title of book:");
                String titleBook = sc.nextLine();
                titleBook += sc.nextLine();

                bookDelivery(titleBook);
            }
        }
        while (option != 0);
    }


    /**
     * Method for enter valid date with scanner
     * @param sc scanner
     * @return object date
     */

    private Date enterDate(Scanner sc){
        Date date;
        do{
            System.out.println("Enter date in format day/month/year");
            String dateString=sc.nextLine();
            dateString+=sc.next();

            String [] dateSplit = dateString.split("/");

            //if split is invalid
            if (dateSplit.length!=3) date=new Date(-1,-1,-1);

            else{
                int day=Integer.parseInt(dateSplit[0]);
                int month=Integer.parseInt(dateSplit[1]);
                int year=Integer.parseInt(dateSplit[2]);

                date=new Date(day,month,year);
            }


        }
        //while date is equal a date with day=-1,month=-1,year=-1 (invalid date)
        while(date.compareDate(new Date(-1,-1,-1))==0);

        return date;
    }

    /**
     * Method for enter valid integer number with scanner
     * @param sc scanner
     * @return int number
     */

    private int insertIntNumber(Scanner sc){
        int num;
        while (true) {
            //if scanner is integer number
            if (sc.hasNextInt()) {
                //read integer number
                num = sc.nextInt();
                return num;
            } else {
                //clean scanner
                sc.next();
                System.out.println("Wrong value!\nEnter a number");
            }
        }
    }

    /**
     * Add book to library book list
     *
     * @param authorName author of book
     * @param titleBook  title of book
     */

    private void addBook(String authorName, String titleBook) {
        //add book in the list of books and define that book is available with (boolean available=true)
        books.add(new Book(authorName, titleBook, true));
    }

    /**
     * Add reader to readers list
     *
     * @param readerName   readerName
     * @param readerNumber readerNumber
     */

    private void addReader(String readerName, int readerNumber) {
        readers.add(new Reader(readerName, readerNumber));
    }

    /**
     * Search book with the title of book
     *
     * @param titleBook string for search title of book
     * @return Book with a title searched in string
     */

    private Book searchBook(String titleBook) {
        for (Book book : books) {
            //if title of book is equal a introduced string
            if (book.getTitleBook().equals(titleBook)) {
                //return book with titleBook is equals a String titleBook
                return book;
            }
        }
        //if book not exists, return null
        return null;
    }

    /**
     * Create new requisition:
     * Initially search book with title is equal a titleBook
     * Apply requisition method defined in book class
     *
     * @param titleBook   string with title of book for searching a book
     * @param indexReader index of reader in list of readers
     * @param req         Introducing requesition date
     * @param dev         Introducing devolucion date
     */

    private void newRequesition(String titleBook, int indexReader, Date req, Date dev) {
        //book that titleBook is equal a title of boook
        Book bookReq = searchBook(titleBook);

        //if book exists in library
        if (bookReq != null) {
            //if book is available (boolean available=true)
            if (bookReq.setAvailable()) {

                Reader reader = readers.get(indexReader);
                Requisition requisition = bookReq.requisitionBook(reader, req, dev);

                //if book is required, so book is not available (boolean available=false)
                bookReq.setAvailable(false);

                //add requisition in reader
                reader.addRequisition(requisition);

                //add requisition in list of requisitions
                requisitions.add(requisition);
            }
            //if available is false, so book is requested
            else System.out.println("Book not available in the moment.\nINVALID REQUISITION.");
        }
        else {
            //book not exists in the library
            System.out.println("Book not exists in library.\nINVALID REQUISITION.");
        }
    }

    /**
     * Search book with titleBook and book becomes available again (boolean available=true)
     * @param titleBook string with title of book for searching a book
     */

    private void bookDelivery (String titleBook) {
        Book book = searchBook(titleBook);
        if (book != null) book.setAvailable(true);
    }

    /**
     * Search requisitions with date
     *
     * @param date Enter date
     */
    private void searchRequesitionDate(Date date) {
        for (Requisition requisition : requisitions) {
            Date reqDate = requisition.getReqDate();
            Date devDate = requisition.getDevDate();
            //if actual date is after (or equal) the requisition date and actual date is before (or equal) the devolucion dat
            if (date.compareDate(reqDate) <= 0 && date.compareDate(devDate) >= 0) {
                System.out.println(requisition);
            }
        }
        System.out.println();
    }

    /**
     * Print all books of available in the moment
     */
    private void getAllAvailableBooks() {
        System.out.println("********************************************************* LIST OF AVAILABLE BOOKS IN THE MOMENT");
        for (Book book : books) {
            if (book.setAvailable()) System.out.println(book);
        }
        System.out.println();
    }

    /**
     * Print all books of library
     */
    private void getAllBooks() {
        System.out.println("********************************************************* LIST OF ALL BOOKS");
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println();
    }

    /**
     * Print all readers
     */
    private void getAllReaders() {
        for (Reader reader : readers) {
            System.out.println(readers.indexOf(reader) + " --> " + reader);
        }
        System.out.println();
    }

    /**
     * Print all requisitions
     */
    private void getAllRequisitions() {
        for (Requisition requisition : requisitions) {
            System.out.println(requisition);
        }
        System.out.println();
    }

    private void getAllRequisitionsReader(Reader reader){
        System.out.println("Requisited books"+" --> " +reader);
        ArrayList<Requisition> requisitions=reader.getRequisitions();
        for (Requisition requisition : requisitions) {
            System.out.println(requisition);
        }
        System.out.println();
    }
}

