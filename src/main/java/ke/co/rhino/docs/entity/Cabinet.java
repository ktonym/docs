package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by user on 7/21/2016.
 */
@Entity
public class Cabinet extends AbstractEntity implements EntityItem<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cabinetId;
    @Enumerated(value = EnumType.STRING)
    private CabinetType cabinetType;
//    @Column(unique = true,nullable = false)
//    private Integer shelfNumber;
    @OneToMany(mappedBy = "cabinet")
    private Set<CabinetRow> cabinetRows;

    public Cabinet() {
    }

    public Cabinet(CabinetBuilder cabinetBuilder) {
        this.cabinetId = cabinetBuilder.cabinetId;
        this.cabinetType = cabinetBuilder.cabinetType;
        //this.shelfNumber = cabinetBuilder.shelfNumber;
//        this.cabinetRows.addAll((cabinetBuilder.cabinetRows).stream().collect(Collectors.toSet()));
    }

    public static class CabinetBuilder{

        private Long cabinetId;
        private CabinetType cabinetType;
        //private Integer shelfNumber;
//        private Set<CabinetRow> cabinetRows;

        public CabinetBuilder() {
        }

        public CabinetBuilder cabinetId(Long cabinetId){
            this.cabinetId = cabinetId;
            return this;
        }

        public CabinetBuilder cabinetType(CabinetType cabinetType){
            this.cabinetType = cabinetType;
            return this;
        }

//        public CabinetBuilder shelfNumber(Integer shelfNumber){
//            this.shelfNumber = shelfNumber;
//            return this;
//        }

        /*public CabinetBuilder cabinetRows(Set<CabinetRow> cabinetRows){
            this.cabinetRows = cabinetRows;
            return this;
        }*/

        public Cabinet build(){
            return new Cabinet(this);
        }

    }

    public Long getCabinetId() {
        return cabinetId;
    }

    public CabinetType getCabinetType() {
        return cabinetType;
    }

//    public Integer getShelfNumber() {
//        return shelfNumber;
//    }

    public Set<CabinetRow> getCabinetRows() {
        return cabinetRows;
    }

    @Override
    public Long getId() {
        return cabinetId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("cabinetId",cabinetId)
                .add("cabinetType", cabinetType.toString())
                .add("description", toString());
    }

    @Override
    public String toString() {
        return "Cabinet{" +
                "cabinetId=" + cabinetId +
                ", cabinetType=" + cabinetType +
                '}';
    }
}
