package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by user on 7/21/2016.
 */
@Entity
public class File extends AbstractEntity implements EntityItem<Long> {

    @Id
    private Long fileId;
    @Column(nullable = false,unique = true)
    private String code;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "file")
    private Set<Circulation> circulations;

    public File() {
    }

    public File(FileBuilder fileBuilder) {
        this.fileId = fileBuilder.fileId;
        this.code = fileBuilder.code;
        this.category = fileBuilder.category;
        this.circulations = fileBuilder.circulations;
    }

    public static class FileBuilder{

        private Long fileId;
        private String code;
        private Category category;
        private Set<Circulation> circulations;

        public FileBuilder() {
        }

        public FileBuilder fileId(Long fileId){
            this.fileId = fileId;
            return this;
        }

        public FileBuilder code(String code){
            this.code = code;
            return this;
        }

        public FileBuilder category(Category category){
            this.category = category;
            return this;
        }

        public FileBuilder circulations(Set<Circulation> circulations){
            this.circulations = circulations;
            return this;
        }

        public File build(){
            return new File(this);
        }
    }

    public Long getFileId() {
        return fileId;
    }

    public String getCode() {
        return code;
    }

    public Category getCategory() {
        return category;
    }

    public Set<Circulation> getCirculations() {
        return circulations;
    }

    @Override
    public Long getId() {
        return fileId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("fileId",fileId)
                .add("code",code);
        category.addJson(builder);
    }
}
