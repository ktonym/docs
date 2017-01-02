package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
@Entity
@Table(name = "USER_GROUP")
public class Group extends AbstractEntity implements EntityItem<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private Set<User> users;
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Group() {
    }

    public Group(GroupBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    public static class GroupBuilder{

        private Long id;
        private final String name;
        private String description;

        public GroupBuilder(String name) {
            this.name = name;
        }

        public GroupBuilder id(Long id){
            this.id = id;
            return this;
        }

        public GroupBuilder description(String description){
            this.description = description;
            return this;
        }

        public Group build(){
            return new Group(this);
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("id", id)
                .add("name", name == null ? "" : name)
                .add("description", description == null ? "" : description);
    }
}
