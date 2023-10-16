package net.FAP.model;

import com.fasterxml.jackson.annotation.JsonView;
import net.FAP.json.View;
import javax.persistence.*;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author acer
 */
@Entity
@Table(name="timetable")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    @Column(name = "timetable_id")
    private int timetableID;

    @ManyToOne
    @JsonView(View.Public.class)
    @JoinColumn(name="class_id")
    private Clss clss;

    @ManyToOne
    @JsonView(View.Public.class)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @JsonView(View.Public.class)
    @Column(name="start_date")
    private Date startDate;

    @JsonView(View.Public.class)
    @Column(name="end_date")
    private Date endDate;

    @JsonView(View.Public.class)
    @Column(name="start_time")
    private Time startTime;

    @JsonView(View.Public.class)
    @Column(name="end_time")
    private Time endTime;

    @ManyToOne
    @JsonView(View.Public.class)
    @JoinColumn(name="lecture")
    private Teacher lecture;

    @JsonView(View.Public.class)
    @JoinColumn(name="slot")
    private int slot;

    private String day;

    @JsonView(View.Public.class)
    @Column(name="department_id")
    private String department;

    @JsonView(View.Public.class)
    @Column(name="room")
    private int room;

    @JsonView(View.Public.class)
    @Column(name="attend_data")
    private boolean attend;

    public Timetable() {
    }

    public boolean isAttend() {
        return attend;
    }

    public void setAttend(boolean attend) {
        this.attend = attend;
    }



    public Teacher getLecture() {
        return lecture;
    }

    public void setLecture(Teacher lecture) {
        this.lecture = lecture;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }





    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getTimetableID() {
        return timetableID;
    }

    public void setTimetableID(int timetableID) {
        this.timetableID = timetableID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }



    public Clss getClss() {
        return clss;
    }

    public void setClss(Clss clss) {
        this.clss = clss;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }


}
