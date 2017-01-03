package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by user on 7/21/2016.
 */
@Entity
public class File extends AbstractEntity implements EntityItem<Long> {

    @Id
    private Long fileId;
    @Column(nullable = false,unique = true)
    private String code;
    @Enumerated(value = EnumType.STRING)
    private FileStatus status;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "file")
    private Set<Circulation> circulations;

    public File() {
    }

    public File(FileBuilder fileBuilder) {
        this.fileId = fileBuilder.fileId;
        this.code = fileBuilder.code;
        this.status = fileBuilder.status;
        this.category = fileBuilder.category;
//        this.circulations.addAll(fileBuilder.circulations.stream().collect(Collectors.toSet()));
    }

    public static class FileBuilder{

        private Long fileId;
        private String code;
        private Category category;
//        private Set<Circulation> circulations;
        public FileStatus status;

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

        public FileBuilder status(FileStatus status){
            this.status = status;
            return this;
        }

        public FileBuilder category(Category category){
            this.category = category;
            return this;
        }

        /*public FileBuilder circulations(Set<Circulation> circulations){
            this.circulations = circulations;
            return this;
        }*/

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

    public FileStatus getStatus() {
        return status;
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
                .add("code",code)
                .add("status", status.toString());
        category.addJson(builder);
    }
}
