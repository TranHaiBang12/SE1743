package net.FAP.model;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import net.FAP.json.View;

/**
 *
 * @author acer
 */
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    @Column(name="student_id")
    private int studentID;

    @JsonView(View.Public.class)
    @Column(name="first_name")
    private String firstName;

    @JsonView(View.Public.class)
    @Column(name="last_name")
    private String lastName;



    public Student() {
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}