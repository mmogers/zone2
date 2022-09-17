package lv.marmog.zone2.zone2.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table (name = "author_details")
public class Author {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
    @Column(name = "author_name")
    private String authorName;

   /* @ManyToOne
    private Country countyOfOrigin;
*/
    //public Author(String name) { this.cargoType = cargoType; }
   public Author(Integer authorId, String authorName) {
       super();
       this.authorId = authorId;
       this.authorName = authorName;
   }


}
