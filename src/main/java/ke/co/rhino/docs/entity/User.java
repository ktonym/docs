package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by user on 7/21/2016.
 */
@Entity
public class User extends AbstractEntity implements EntityItem<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String surname;
    @OneToMany(mappedBy = "user")
    private Set<Circulation> circulations;

    public User() {
    }

    public User(UserBuilder userBuilder) {
        this.userId = userBuilder.userId;
        this.firstName = userBuilder.firstName;
        this.surname = userBuilder.surname;
        this.circulations = userBuilder.circulations;
    }

    public static class UserBuilder{

        private Long userId;
        private String firstName;
        private String surname;
        private Set<Circulation> circulations;

        public UserBuilder() {
        }

        public UserBuilder userId(Long userId){
            this.userId = userId;
            return this;
        }

        public UserBuilder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public UserBuilder surname(String surname){
            this.surname = surname;
            return this;
        }

        public User build(){
            return new User(this);
        }

    }

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Set<Circulation> getCirculations() {
        return circulations;
    }

    @Override
    public Long getId() {
        return userId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("userId", userId)
                .add("firstName", firstName)
                .add("surname", surname)
                .add("fullName", firstName + " " + surname);
    }
}
