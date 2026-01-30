package models;

public class Date {
    private String month;
    private String day;
    private String year;

    public Date(String month, String day, String year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Date(String dateStr) {
        String[] date = dateStr.split("-");
        this.month = date[0];
        this.day = date[1];
        this.year = date[2];
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getYear() {
        return year;
    }

    public boolean equals(Date date) {
        return date.toString().equals(month + "-" + day + "-" + year);
    }

    public String toString() {
        return month + "-" + day + "-" + year;
    }

}
