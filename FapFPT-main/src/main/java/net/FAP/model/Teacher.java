package net.FAP.model;
import com.fasterxml.jackson.annotation.JsonView;
import net.FAP.json.View;
import javax.persistence.*;

@Entity
@Table(name="teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    @Column(name="teacher_id")
    private int teacherID;

    @JsonView(View.Public.class)
    @Column(name="first_name")
    private String firstName;

    @JsonView(View.Public.class)
    @Column(name="last_name")
    private String lastName;

    public Teacher() {
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
