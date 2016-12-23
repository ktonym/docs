package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by user on 7/21/2016.
 */
@Entity
public class Client extends AbstractEntity implements EntityItem<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientId;
    private String clientName;
    private String tel;
    private String email;
    private String pin;
    @ManyToOne
    /*@JoinColumns({
            @JoinColumn(name = "rowNumber", referencedColumnName = "rowNumber"),
            @JoinColumn(name = "cabinetId")
    })*/
    private CabinetRow cabinetRow;
    @OneToMany(mappedBy = "client")
    private Set<Category> categories;

    public Client() {
    }

    public Client(ClientBuilder clientBuilder) {
        this.clientId = clientBuilder.clientId;
        this.clientName = clientBuilder.clientName;
        this.tel = clientBuilder.tel;
        this.email = clientBuilder.email;
        this.pin = clientBuilder.pin;
        this.cabinetRow = clientBuilder.cabinetRow;
        //this.categories.addAll(clientBuilder.categories.stream().collect(Collectors.toSet()));
    }

    public static class ClientBuilder{
        private Long clientId;
        private final String clientName;
        private String tel;
        private String email;
        private String pin;
        private CabinetRow cabinetRow;
        private Set<Category> categories;

        public ClientBuilder(String clientName) {
            this.clientName = clientName;
        }

        public ClientBuilder clientId(Long clientId){
            this.clientId = clientId;
            return this;
        }

        public ClientBuilder tel(String tel){
            this.tel = tel;
            return this;
        }

        public ClientBuilder pin(String pin){
            this.pin = pin;
            return this;
        }

        public ClientBuilder cabinetRow(CabinetRow cabinetRow){
            this.cabinetRow = cabinetRow;
            return this;
        }

        public ClientBuilder categories(Set<Category> categories){
            this.categories = categories;
            return this;
        }

        public Client build(){
            return new Client(this);
        }

        public ClientBuilder email(String email) {
            this.email = email;
            return this;
        }
    }

    public Long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getPin() {
        return pin;
    }

    public CabinetRow getCabinetRow() {
        return cabinetRow;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @Override
    public Long getId() {
        return clientId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("clientId",clientId)
                .add("clientName", clientName)
                .add("tel",tel == null ? "" : tel)
                .add("email",email == null ? "" : email)
                .add("pin",pin == null ? "" : pin);
        cabinetRow.addJson(builder);
    }
}
