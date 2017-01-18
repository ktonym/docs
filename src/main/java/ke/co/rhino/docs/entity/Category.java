package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by user on 7/21/2016.
 */
@Entity
public class Category extends AbstractEntity implements EntityItem<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    @Column(unique = true,nullable = false)
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private Set<File> files;

    public Category() {
    }

    public Category(CategoryBuilder categoryBuilder) {
        this.categoryId = categoryBuilder.categoryId;
        this.description = categoryBuilder.description;
        this.name = categoryBuilder.name;
    }

    public static class CategoryBuilder{

        private Long categoryId;
        private String description;
        public String name;

        public CategoryBuilder() {
        }

        public CategoryBuilder name(String name){
            this.name = name;
            return  this;
        }

        public CategoryBuilder description(String description){
            this.description = description;
            return this;
        }

        public CategoryBuilder categoryId(Long categoryId){
            this.categoryId = categoryId;
            return this;
        }

        public Category build(){
            return new Category(this);
        }

    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    public Set<File> getFiles() {
        return files;
    }

    @Override
    public Long getId() {
        return categoryId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("categoryId",categoryId)
                .add("description",description==null?"":description)
                .add("name",name);
    }
}
