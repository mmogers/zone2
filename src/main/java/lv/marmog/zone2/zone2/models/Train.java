package lv.marmog.zone2.zone2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity

public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtrain;


    private Integer numberOfVagons;

    @ManyToOne
    private Country destination;

    @ManyToOne
    private Country fromCountry;

    private String serialNumber;

    /*@OneToMany(
            mappedBy = "vagon",
            cascade = CascadeType.ALL
    )*/
    //private List <Vagon> vagons;

    public Train (String serialNumber) {this.serialNumber = serialNumber;}
}
