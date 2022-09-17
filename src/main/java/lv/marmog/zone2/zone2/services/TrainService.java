/*
package lv.marmog.zone2.zone2.services;

import lv.marmog.zone2.zone2.models.Inputs.TrainInput;
import lv.marmog.zone2.zone2.models.Train;
import lv.marmog.zone2.zone2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainService {
    @Autowired
    BookRepository bookRepository;



    public int createTrain(TrainInput trainInput) {
        Train train = new Train();
        train.setSerialNumber(trainInput.getSerialNumber());
        train.setDestination(trainInput.getDestination());
        train.setFromCountry(trainInput.getFromBook());
        train.setNumberOfWagons(0);
        bookRepository.save(train);

        return train.getIdtrain();

    }
}
*/
