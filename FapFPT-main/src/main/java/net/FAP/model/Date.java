package net.FAP.model;

public class Date {
    private String day;
    private Date d;
    private String dS;

    public Date() {
    }

    public Date(String day, Date d, String dS) {
        this.day = day;
        this.d = d;
        this.dS = dS;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public String getdS() {
        return dS;
    }

    public void setdS(String dS) {
        this.dS = dS;
    }


}
