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
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@SQLDelete(sql=
        "UPDATE CargoType" +
                "SET deleted_at = NOW()" +
                "WHERE id = ?")
@Loader(namedQuery = "findCargoTypeById")
@NamedQuery(name = "findCargoTypeById", query =
        "FROM CargoType c " +
                "WHERE " +
                "c.id = :id AND " +
                "deleted_at IS NULL")
@Where(clause = "deleted_at IS NULL")
public class CargoType extends UniqueEntity {

    @Id
    @SequenceGenerator(name = "cargo_type_seq_gen", sequenceName = "cargo_type_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_type_seq_gen")
    @JsonIgnore
    private Integer id;

    private String name;

    public CargoType (String name) { this.name = name; }

    public CargoType (UUID uuid) { this.uuid = uuid; }


}
