/*
package lv.marmog.zone2.zone2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity

public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtrain;


    private Integer numberOfWagons;

    @ManyToOne
    private Country destination;

    @ManyToOne
    private Country fromCountry;

    private String serialNumber;

    @OneToMany(
            mappedBy = "train",
            cascade = CascadeType.ALL
    )
    private List <Wagon> wagons;

    @ManyToOne
    private Terminal terminal;
    public Train (String serialNumber) {this.serialNumber = serialNumber;}
}
*/
