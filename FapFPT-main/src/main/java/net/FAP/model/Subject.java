package net.FAP.model;


import com.fasterxml.jackson.annotation.JsonView;
import net.FAP.json.View;
import javax.persistence.*;

@Entity
@Table(name="subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    @Column(name="subject_id")
    private int subjectID;

    @JsonView(View.Public.class)
    @Column(name="subject_name")
    private String subjectName;

    public Subject() {
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
