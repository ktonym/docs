package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private String pin;

    public Client() {
    }

    public Client(ClientBuilder clientBuilder) {
        this.clientId = clientBuilder.clientId;
        this.clientName = clientBuilder.clientName;
        this.tel = clientBuilder.tel;
        this.pin = clientBuilder.pin;
    }

    public static class ClientBuilder{
        private Long clientId;
        private final String clientName;
        private String tel;
        private String pin;

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

        public Client build(){
            return new Client(this);
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

    public String getPin() {
        return pin;
    }

    @Override
    public Long getId() {
        return clientId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("clientId",clientId)
                .add("clientName", clientName)
                .add("tel",tel)
                .add("pin",pin);
    }
}
