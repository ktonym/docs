package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.List;

/**
 * Created by anthony.kipkoech on 1/3/2017.
 */
public class CategoryRef extends AbstractEntity implements EntityItem<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true,nullable = false)
    private String name;
    @OneToMany(mappedBy = "categoryRef")
    private List<Category> categories;

    public CategoryRef() {
    }

    public CategoryRef(CategoryRefBuilder builder) {
        this.name = builder.name;
        this.id = builder.id;
    }

    public static class CategoryRefBuilder{
        private Long id;
        private final String name;

        public CategoryRefBuilder(String name) {
            this.name = name;
        }

        public CategoryRefBuilder id(Long id){
            this.id = id;
            return this;
        }

        public CategoryRef build(){
            return new CategoryRef(this);
        }

    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("categoryRefId", id).add("name", name);
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
