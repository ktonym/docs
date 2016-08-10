package ke.co.rhino.docs.entity;

import java.io.Serializable;

/**
 * Created by akipkoech on 10/08/2016.
 */
public class CabinetRowId implements Serializable {

    Long rowNumber;
    Long cabinetId;

    public CabinetRowId(Long rowNumber, Long cabinetId) {
        this.rowNumber = rowNumber;
        this.cabinetId = cabinetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CabinetRowId)) return false;

        CabinetRowId that = (CabinetRowId) o;

        if (!rowNumber.equals(that.rowNumber)) return false;
        return cabinetId.equals(that.cabinetId);

    }

    @Override
    public int hashCode() {
        int result = rowNumber.hashCode();
        result = 31 * result + cabinetId.hashCode();
        return result;
    }
}
