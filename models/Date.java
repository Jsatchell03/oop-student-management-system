package models;

public class Date {
    private int month;
    private int day;
    private int year;

    public Date(int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public boolean equals(Date date){
        return date.getMonth() == month && date.getDay() == day && date.getYear() == year;
    }

    public String toString(){
        return String.format("%d-%d-%d", month, day, year);
    }

    public static Date parseString(String date){
        String[] splitDate = date.split("-");
        return new Date(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]));
    }
}
