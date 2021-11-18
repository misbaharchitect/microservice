package hibernatefundamentals.model;

import javax.persistence.*;

@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "User.nameFilter", query = "from User u " +
                "                where u.id > :userId and u.name like :userName " +
                "                order by u.name")
})
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
