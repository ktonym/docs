package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
@Entity
public class Role extends AbstractEntity implements EntityItem<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne(optional = true)
    private Group group;
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;

    public Role() {
    }

    public Role(RoleBuilder roleBuilder) {
        this.id = roleBuilder.id;
        this.name = roleBuilder.name;
        this.group = roleBuilder.group;
    }

    public static class RoleBuilder{
        private Long id;
        private String name;
        private Group group;

        public RoleBuilder() {
        }

        public RoleBuilder id(Long id){
            this.id = id;
            return this;
        }

        public RoleBuilder name(String name){
            this.name = name;
            return this;
        }

        public RoleBuilder group(Group group){
            this.group = group;
            return this;
        }

        public Role build(){
            return new Role(this);
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Group getGroup() {
        return group;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("id", id)
                .add("name", name == null ? "" : name);
        group.addJson(builder);
    }
}
