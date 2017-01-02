package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by anthony.kipkoech on 12/28/2016.
 */
@Entity
public class PasswordHistory extends AbstractEntity implements EntityItem<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private String password;
    @Convert(converter = LocalDatePersistenceConverter.class)
    private LocalDate changed;

    static final DateTimeFormatter DATE_FORMATTER_yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public PasswordHistory() {
    }

    public PasswordHistory(User user, String password, LocalDate changed) {
        this.user = user;
        this.password = password;
        this.changed = changed;
    }

    @Override
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getChanged() {
        return changed;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {

    }
}
