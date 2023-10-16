package net.FAP.model;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import net.FAP.json.View;

import java.io.Serializable;

/**
 *
 * @author acer
 */
@Entity
@IdClass(StudentClass.class)
@Table(name="student_class")
public class StudentClass implements Serializable {
    @ManyToOne
    @Id
    @JsonView(View.Public.class)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne
    @Id
    @JsonView(View.Public.class)
    @JoinColumn(name="class_id")
    private Clss clss;

    public StudentClass() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Clss getClss() {
        return clss;
    }

    public void setClss(Clss clss) {
        this.clss = clss;
    }





}
