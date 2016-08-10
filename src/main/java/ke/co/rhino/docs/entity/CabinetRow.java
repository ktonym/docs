package ke.co.rhino.docs.entity;


import javax.json.JsonObjectBuilder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by akipkoech on 22/07/2016.
 */
@Entity
public class CabinetRow extends AbstractEntity implements EntityItem<CabinetRowId>{

    @Id
    private Long rowNumber;
    @Id
    @ManyToOne
    private Cabinet cabinet;
    @OneToMany(mappedBy = "row")
    private Set<Client> clients;

    public CabinetRow() {
    }

    public CabinetRow(CabinetRowBuilder cabinetRowBuilder) {
        this.rowNumber = cabinetRowBuilder.rowNumber;
        this.cabinet = cabinetRowBuilder.cabinet;
        clients.addAll((cabinetRowBuilder.clients).stream().collect(Collectors.toSet()));
    }

    public static class CabinetRowBuilder{

        private final Long rowNumber;
        private Cabinet cabinet;
        private Set<Client> clients;

        public CabinetRowBuilder(Long rowNumber) {
            this.rowNumber = rowNumber;
        }

        public CabinetRowBuilder cabinet(Cabinet cabinet){
            this.cabinet = cabinet;
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

    public Set<Client> getClients() {
        return clients;
    }

    @Override
    public CabinetRowId getId() {
        return new CabinetRowId(rowNumber,cabinet.getId());
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("rowNumber",rowNumber);
        cabinet.addJson(builder);
    }
}
