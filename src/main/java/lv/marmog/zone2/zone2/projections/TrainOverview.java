/*
package lv.marmog.zone2.zone2.projections;

import lv.marmog.zone2.zone2.models.Book;
import lv.marmog.zone2.zone2.models.Terminal;
import lv.marmog.zone2.zone2.models.Wagon;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface TrainOverview {

    Integer getIdtrain();

    @Value("#{target.wagons.size()}")
    Integer getNumberOfWagons();

    Book getDestination();

    Book getFromCountry();

    String getSerialNumber();

    List<Wagon> getWagons();

    Terminal getTerminal();

}
*/
