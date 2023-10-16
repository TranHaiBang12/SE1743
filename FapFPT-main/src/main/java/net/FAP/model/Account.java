package net.FAP.model;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import net.FAP.json.View;

/**
 *
 * @author acer
 */
@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonView(View.Public.class)
    @Column(name="account_id")
    private int accountID;

    @JsonView(View.Public.class)
    @Column(name="username")
    private String username;

    @JsonView(View.Public.class)
    @Column(name="password")
    private String password;

    @JsonView(View.Public.class)
    @Column(name="role")
    private String role;

    @ManyToOne
    @JsonView(View.Public.class)
    @JoinColumn(name = "user_id")
    private Teacher teacher;



    public Account() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
