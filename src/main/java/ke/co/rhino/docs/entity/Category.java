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
    @ManyToOne
    private CategoryRef categoryRef;
    private String description;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "category")
    private Set<File> files;

    public Category() {
    }

    public Category(CategoryBuilder categoryBuilder) {
        this.categoryId = categoryBuilder.categoryId;
        this.description = categoryBuilder.description;
        this.categoryRef = categoryBuilder.categoryRef;
        this.client = categoryBuilder.client;
    }

    public static class CategoryBuilder{

        private Long categoryId;
        private String description;
        private final Client client;
        private Set<File> files;
        public CategoryRef categoryRef;

        public CategoryBuilder(Client client) {
            this.client = client;
        }

        public CategoryBuilder categoryRef(CategoryRef categoryRef){
            this.categoryRef = categoryRef;
            return this;
        }

        public CategoryBuilder description(String description){
            this.description = description;
            return this;
        }

        public CategoryBuilder categoryId(Long categoryId){
            this.categoryId = categoryId;
            return this;
        }

        public CategoryBuilder files(Set<File> files){
            this.files = files;
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

    public CategoryRef getCategoryRef() {
        return categoryRef;
    }

    public Set<File> getFiles() {
        return files;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public Long getId() {
        return categoryId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("categoryId",categoryId)
                .add("description",description==null?"":description);
        client.addJson(builder);
        categoryRef.addJson(builder);
    }
}
