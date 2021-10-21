package LibraryGestion;

public class Date {

    //attributes of the date
    private int day;
    private int month;
    private int year;

    /**
     * Create new date, if date is invalid create a date with day=-1,month=-1,year=-1
     */
    public Date(int day,int month,int year){
        setDate(day,month,year);
    }

    private void setDate(int day, int month, int year){
        if (verificateDate(day,month,year)){
            this.day=day;
            this.month=month;
            this.year=year;
        }
        else {
            this.day=-1;
            this.month=-1;
            this.year=-1;
        }
    }

    /**
     * Verificate if date is valid or invalide
     * @param day day
     * @param month month
     * @param year year
     * @return true (if date is valid) or false(if date is invalid)
     */
    private boolean verificateDate(int day,int month,int year) {
        if (day < 1 || month < 1 || month > 12 || year < 0) return false;
        if (day <=28 && month == 2) return true;
        if (day<=30 && (month==4||month==6||month==9||month==11)) return true;
        return day <= 31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    /**
     * Verificate if a two dates is equals
     * @param date date for comparing
     * @return result of comparing
     */
    public int compareDate(Date date){
        if (date.getYear()!=year) return date.getYear()-year;
        if (date.getMonth()!=month) return date.getMonth()-month;
        return date.getDay()-day;
    }

    @Override
    public String toString() {
        return day+"/"+month+"/"+year;
    }
}
