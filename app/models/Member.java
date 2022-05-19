package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model {
    public String firstName;
    public String lastName;
    @Column(unique=true)
    public String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Station> stations = new ArrayList<>();

    public Member(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean isCorrectPassword(String password) {
        return this.password.equals(password);
    }

    public void sortStationsByName() {
        stations.sort((station1, station2) -> station1.name.compareToIgnoreCase(station2.name));
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
