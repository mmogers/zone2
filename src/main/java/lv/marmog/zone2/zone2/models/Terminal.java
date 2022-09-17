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
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idterminal;

    private String name;

    @OneToMany(mappedBy = "terminal",
    cascade = CascadeType.ALL)
    private List<Train> trains;
}
*/
