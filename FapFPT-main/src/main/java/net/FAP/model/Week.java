package net.FAP.model;

import java.sql.Date;

/**
 *
 * @author acer
 */
public class Week {
    private int weekID;
    private Date start;
    private Date end;
    private String startS;
    private String endS;

    public Week() {
    }

    public Week(int weekID, Date start, Date end) {
        this.weekID = weekID;
        this.start = start;
        this.end = end;

    }

    public String getStartS() {
        return startS;
    }

    public void setStartS(String startS) {
        this.startS = startS;
    }

    public String getEndS() {
        return endS;
    }

    public void setEndS(String endS) {
        this.endS = endS;
    }



    public int getWeekID() {
        return weekID;
    }

    public void setWeekID(int weekID) {
        this.weekID = weekID;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }


}