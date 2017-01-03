package ke.co.rhino.docs.entity;

import sun.text.normalizer.UBiDiProps;

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
    private String username;
    private String password;
    private String firstName;
    private String surname;
    private Boolean locked;
    private Boolean expired;
    private int failedLogins;
    @OneToMany(mappedBy = "user")
    private Set<Circulation> circulations;
    @ManyToOne(cascade = CascadeType.ALL)
    private Group group;
    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles;
    @OneToMany(mappedBy = "user")
    private Set<PasswordHistory> passwordHistorySet;

    public User() {
    }

    public User(UserBuilder userBuilder) {
        this.userId = userBuilder.userId;
        this.username = userBuilder.username;
        this.password = userBuilder.password;
        this.firstName = userBuilder.firstName;
        this.surname = userBuilder.surname;
        this.locked = userBuilder.locked;
        this.expired = userBuilder.expired;
        this.group = userBuilder.group;
        this.failedLogins = userBuilder.failedLogins;
        //this.circulations = userBuilder.circulations;
    }

    public static class UserBuilder{

        private Long userId;
        private String username;
        private String password;
        private String firstName;
        private String surname;
        private Group group;
        private Boolean locked;
        private Boolean expired;
        public int failedLogins;
        //private Set<Circulation> circulations;

        public UserBuilder(String username) {
            this.username = username;
        }

        public UserBuilder userId(Long userId){
            this.userId = userId;
            return this;
        }

        /*public UserBuilder username(String username){
            this.username = username;
            return this;
        }*/

        public UserBuilder password(String password){
            this.password  = password;
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

        public UserBuilder group(Group group){
            this.group = group;
            return this;
        }

        public UserBuilder locked(Boolean locked){
            this.locked = locked;
            return this;
        }

        public UserBuilder expired(Boolean expired){
            this.expired = expired;
            return this;
        }

        public User build(){
            return new User(this);
        }

        public UserBuilder failedLogins(int failedLogins) {
            this.failedLogins = failedLogins;
            return this;
        }
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Group getGroup() {
        return group;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Boolean getLocked() {
        return locked;
    }

    public Boolean getExpired() {

        return expired;// == null ? false : expired.equals('Y');
    }

    public int getFailedLogins() {
        return failedLogins;
    }

    public Set<Circulation> getCirculations() {
        return circulations;
    }

    public Set<PasswordHistory> getPasswordHistorySet() {
        return passwordHistorySet;
    }

    public String getFullName(){
        return firstName.concat(" ").concat(surname);
    }

    @Override
    public Long getId() {
        return userId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("userId", userId)
                .add("username", username)
                .add("firstName", firstName)
                .add("surname", surname)
                .add("locked", locked)
                .add("expired", expired )
                .add("fullName", firstName + " " + surname);
        group.addJson(builder);
    }
}
