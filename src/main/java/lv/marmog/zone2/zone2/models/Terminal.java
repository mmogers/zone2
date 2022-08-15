package lv.marmog.zone2.zone2.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class Terminal {

    @Id
    private Integer idterminal;

    private String name;
}
