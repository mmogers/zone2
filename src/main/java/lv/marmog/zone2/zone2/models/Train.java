package lv.marmog.zone2.zone2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@SQLDelete(sql=
        "UPDATE Train" +
                "SET deleted_at = NOW()" +
                "WHERE id = ?")
@Loader(namedQuery = "findTrainById")
@NamedQuery(name = "findTrainById", query =
        "FROM Train t " +
                "WHERE " +
                "t.id = :id AND " +
                "deleted_at IS NULL")
@Where(clause = "deleted_at IS NULL")

public class Train extends UniqueEntity{

    @Id
    @SequenceGenerator(name = "train_seq_gen", sequenceName = "train_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "train_seq_gen")
    @JsonIgnore
    private int id;


    private Integer numberOfVagons;

    private String fromLocation;

    private String toLocation;

    private String name;

    @OneToMany(
            mappedBy = "vagon",
            cascade = CascadeType.ALL
    )
    private List <Vagon> vagons;

    public Train (UUID uuid){super (uuid);}

}
