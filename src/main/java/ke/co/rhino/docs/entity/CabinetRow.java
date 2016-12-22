package ke.co.rhino.docs.entity;


import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by akipkoech on 22/07/2016.
 */
@Entity
@Table(uniqueConstraints ={@UniqueConstraint(columnNames = {"ROWNUMBER","CABINET"})})
public class CabinetRow extends AbstractEntity implements EntityItem<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cabinetRowId;
    @Column(name = "ROWNUMBER")
    private Long rowNumber;
    @ManyToOne
    @JoinColumn(name = "CABINET")
    private Cabinet cabinet;
    @OneToMany(mappedBy = "cabinetRow")
    private Set<Client> clients;

    public CabinetRow() {
    }

    public CabinetRow(CabinetRowBuilder cabinetRowBuilder) {
        this.cabinetRowId = cabinetRowBuilder.rowId;
        this.rowNumber = cabinetRowBuilder.rowNumber;
        this.cabinet = cabinetRowBuilder.cabinet;
       // clients.addAll((cabinetRowBuilder.clients).stream().collect(Collectors.toSet()));
    }

    public static class CabinetRowBuilder{

        private Long rowId;
        private Long rowNumber;
        private Cabinet cabinet;
        private Set<Client> clients;

        public CabinetRowBuilder() {
        }

        public CabinetRowBuilder cabinet(Cabinet cabinet){
            this.cabinet = cabinet;
            return this;
        }

        public CabinetRowBuilder rowId(Long rowId){
            this.rowId = rowId;
            return this;
        }

        public CabinetRowBuilder rowNumber(Long rowNumber){
            this.rowNumber = rowNumber;
            return this;
        }

        public CabinetRowBuilder clients(Set<Client> clients){
            this.clients = clients;
            return this;
        }

        public CabinetRow build(){
            return new CabinetRow(this);
        }

    }

    public Long getRowNumber() {
        return rowNumber;
    }

    public Cabinet getCabinet() {
        return cabinet;
    }

    public Long getCabinetRowId() {
        return cabinetRowId;
    }

    public Set<Client> getClients() {
        return clients;
    }

    @Override
    public Long getId() {
        return cabinetRowId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("rowNumber",rowNumber)
                .add("rowId",cabinetRowId);
        cabinet.addJson(builder);
    }
}
