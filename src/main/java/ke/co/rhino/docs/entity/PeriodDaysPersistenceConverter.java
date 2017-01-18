package ke.co.rhino.docs.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Period;

/**
 * Created by anthony.kipkoech on 1/8/2017.
 */
@Converter(autoApply = true)
public class PeriodDaysPersistenceConverter implements AttributeConverter<Period, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Period entityValue) {
        return (entityValue == null ? null : entityValue.getDays());
    }

    @Override
    public Period convertToEntityAttribute(Integer dbValue) {
        return (dbValue == null ? null : Period.ofDays(dbValue));
    }
}
