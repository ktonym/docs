package ke.co.rhino.docs.entity;

import java.io.Serializable;

/**
 * Created by akipkoech on 10/08/2016.
 */
public class CabinetRowId implements Serializable {

    Long rowNumber;
    Cabinet cabinet;

    public CabinetRowId(Long rowNumber, Cabinet cabinet) {
        this.rowNumber = rowNumber;
        this.cabinet = cabinet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CabinetRowId that = (CabinetRowId) o;

        if (!rowNumber.equals(that.rowNumber)) return false;
        return cabinet.equals(that.cabinet);

    }

    @Override
    public int hashCode() {
        int result = rowNumber.hashCode();
        result = 31 * result + cabinet.hashCode();
        return result;
    }
}
