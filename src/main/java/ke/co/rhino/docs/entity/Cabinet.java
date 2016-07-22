package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;

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


    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

    }
}
