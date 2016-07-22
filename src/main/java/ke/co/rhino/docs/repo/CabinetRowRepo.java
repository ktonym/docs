package ke.co.rhino.docs.repo;

import ke.co.rhino.docs.entity.Cabinet;
import ke.co.rhino.docs.entity.CabinetRow;

import java.util.Set;

/**
 * Created by akipkoech on 22/07/2016.
 */
public interface CabinetRowRepo {

    Set<CabinetRow> findByCabinet(Cabinet cabinet);

    long countByCabinet(Cabinet cabinet);

}
