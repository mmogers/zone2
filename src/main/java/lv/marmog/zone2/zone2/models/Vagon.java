package lv.marmog.zone2.zone2.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.util.UUID;

import javax.persistence.*;



@Getter
@Setter
@ToString (callSuper = true)
@NoArgsConstructor
@Entity
@SQLDelete(sql=
        "UPDATE Vagon" +
        "SET deleted_at = NOW()" +
        "WHERE id = ?")
@Loader(namedQuery = "findVagonById")
@NamedQuery(name = "findVagonById", query =
        "FROM Vagon v " +
        "WHERE " +
        "v.id = :id AND " +
        "deleted_at IS NULL")
@Where(clause = "deleted_at IS NULL")
public class Vagon extends UniqueEntity {

    @Id
    @SequenceGenerator(name = "vagon_seq_gen", sequenceName = "vagon_id_seq", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "vagon_seq_gen")
    @JsonIgnore
    private Integer id;

    private Double maxCapacityInTons;
    private Double currentCapacityInTons;

    @ManyToOne
    private Train train;

    private String serialNumber;

    @ManyToOne
    private CargoType cargoType;

    public Vagon (UUID uuid){super (uuid);}

}
