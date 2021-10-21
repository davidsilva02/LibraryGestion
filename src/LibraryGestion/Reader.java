package LibraryGestion;

import java.util.ArrayList;

public class Reader {
    //attributes of the reader
    private final String readerName;
    private final int readerNumber;

    ArrayList<Requisition> requisitions;

    /**
     * Create reader with name and number of the reader
     * @param readerName name of the reader
     * @param readerNumber reader number
     */

    public Reader(String readerName, int readerNumber) {
        this.readerName = readerName;
        this.readerNumber = readerNumber;
        requisitions=new ArrayList<>();
    }

    /**
     * Add requisition a list of requesition in reader
     * @param requisition requisition
     */

    public void addRequisition(Requisition requisition){
        requisitions.add(requisition);
    }

    public String getReaderName() {
        return readerName;
    }

    public int getReaderNumber() {
        return readerNumber;
    }

    public ArrayList<Requisition> getRequisitions() {
        return requisitions;
    }

    public String toString(){
        return "Reader Name: "+readerName+"  "+"Reader Number: "+readerNumber;
    }

}
