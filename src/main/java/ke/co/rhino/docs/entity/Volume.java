package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by anthony.kipkoech on 1/7/2017.
 */
@Entity@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"volumeNo","client"}))
public class Volume extends AbstractEntity implements EntityItem<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long volumeId;
    @Column(name = "volumeNo")
    private Integer volumeNo;
    //@Convert()
    private Year year;
    @OneToMany(mappedBy = "volume")
    private List<File> files;
    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;

    private static final DateTimeFormatter DATE_FORMATTER_yyyy = DateTimeFormatter.ofPattern("yyyy");

    public Volume() {
    }

    public Volume(VolumeBuilder builder) {
        this.volumeId = builder.volumeId;
        this.volumeNo = builder.volumeNo;
        this.year = builder.year;
        this.client = builder.client;
    }


    public static class VolumeBuilder{
        private Long volumeId;
        private final Integer volumeNo;
        private Year year;
        public Client client;

        public VolumeBuilder(Integer volumeNo) {
            this.volumeNo = volumeNo;
        }

        public VolumeBuilder volumeId(Long volumeId){
            this.volumeId = volumeId;
            return this;
        }

        public VolumeBuilder year(Year year){
            this.year = year;
            return this;
        }

        public VolumeBuilder client(Client client){
            this.client = client;
            return this;
        }

        public Volume build(){
            return new Volume(this);
        }
    }

    public Long getVolumeId() {
        return volumeId;
    }

    public Integer getVolumeNo() {
        return volumeNo;
    }

    public Year getYear() {
        return year;
    }

    public List<File> getFiles() {
        return files;
    }

    public Client getClient(){
        return client;
    }

    @Override
    public Long getId() {
        return volumeId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("volumeId", volumeId)
                .add("volumeNo", volumeNo)
                .add("year", year == null ? "" : DATE_FORMATTER_yyyy.format(year));
        client.addJson(builder);
    }
}
