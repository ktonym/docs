package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"ROLE","USER"})})
public class UserRole extends AbstractEntity implements EntityItem<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ROLE")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "USER")
    private User user;

    public UserRole() {
    }

    public UserRole(UserRoleBuilder builder) {
        this.role = builder.role;
        this.user = builder.user;
    }

    public static class UserRoleBuilder{
        private Long id;
        private final Role role;
        private final User user;

        public UserRoleBuilder(Role role, User user) {
            this.role = role;
            this.user = user;
        }

        public UserRole build(){
            return new UserRole(this);
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("id", id);
        role.addJson(builder);
        user.addJson(builder);
    }
}
