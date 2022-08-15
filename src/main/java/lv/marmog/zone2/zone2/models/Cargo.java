package lv.marmog.zone2.zone2.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class Cargo {

    @Id
    private Integer idcargo;

    private String cargoType;

    @ManyToOne
    private Country countyOfOrigin;

    public Cargo(String name) { this.cargoType = cargoType; }


}
