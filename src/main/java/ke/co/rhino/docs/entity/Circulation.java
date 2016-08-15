package ke.co.rhino.docs.entity;

import javax.json.JsonObjectBuilder;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

/**
 * Created by user on 7/21/2016.
 */
@Entity
public class Circulation extends AbstractEntity implements EntityItem<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long circulationId;
    private LocalDate borrowed;
    private Period duration;
    private LocalDate returned;
    @ManyToOne
    private User user;
    @ManyToOne
    private File file;

    public Circulation() {
    }

    public Circulation(CirculationBuilder circulationBuilder) {
        this.circulationId = circulationBuilder.circulationId;
        this.borrowed = circulationBuilder.borrowed;
        this.duration = circulationBuilder.duration;
        this.returned = circulationBuilder.returned;
        this.user = circulationBuilder.user;
        this.file = circulationBuilder.file;
    }

    public static class CirculationBuilder{
        private Long circulationId;
        private LocalDate borrowed;
        private Period duration;
        private LocalDate returned;
        private User user;
        private File file;

        public CirculationBuilder() {
        }

        public CirculationBuilder circulationId(Long circulationId){
            this.circulationId = circulationId;
            return this;
        }

        public CirculationBuilder borrowed(LocalDate borrowed){
            this.borrowed = borrowed;
            return this;
        }

        public CirculationBuilder duration(Period duration){
            this.duration = duration;
            return this;
        }

        public CirculationBuilder returned(LocalDate returned){
            this.returned = returned;
            return this;
        }

        public CirculationBuilder user(User user){
            this.user=user;
            return this;
        }

        public CirculationBuilder file(File file){
            this.file = file;
            return this;
        }

        public Circulation build(){
            return new Circulation(this);
        }
    }

    public Long getCirculationId() {
        return circulationId;
    }

    public LocalDate getBorrowed() {
        return borrowed;
    }

    public Period getDuration() {
        return duration;
    }

    public User getUser() {
        return user;
    }

    public File getFile() {
        return file;
    }

    @Override
    public Long getId() {
        return circulationId;
    }

    @Override
    public void addJson(JsonObjectBuilder builder) {
        builder.add("circulationId",circulationId)
                .add("borrowed",borrowed == null ? "" : DATE_FORMATTER_yyyyMMdd.format(borrowed))
                .add("duration",duration == null ? 0 : duration.getDays())
                .add("returned",returned == null ? "" : DATE_FORMATTER_yyyyMMdd.format(returned));
        user.addJson(builder);
        file.addJson(builder);
    }
}
