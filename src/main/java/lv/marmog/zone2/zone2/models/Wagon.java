/*
package lv.marmog.zone2.zone2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;



@Getter
@Setter
@ToString (callSuper = true)
@NoArgsConstructor
@Entity

public class Wagon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idwagon;

    private Double maxCapacity;
    private Double currentCapacity;

    @ManyToOne
    private Train train;

    private String serialNumber;

    @ManyToOne
    private Author cargo;

    private boolean isEmpty;

}
*/
