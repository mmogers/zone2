package lv.marmog.zone2.zone2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

import javax.persistence.*;



@Getter
@Setter
@ToString (callSuper = true)
@NoArgsConstructor
@Entity

public class Vagon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idvagon;

    private Double maxCapacity;
    private Double currentCapacity;

    @ManyToOne
    private Train train;

    private String serialNumber;

    @ManyToOne
    private Cargo cargo;

    private boolean isEmpty;

}
