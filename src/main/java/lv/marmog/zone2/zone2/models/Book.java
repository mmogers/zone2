package lv.marmog.zone2.zone2.models;


import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "book_details")
public class Book {
    @Id
    private Integer bookCode;

    @Column(name = "book_name")
    private String bookName;

    @CreationTimestamp
    @Column(name = "added_on", nullable = false, updatable = false)
    private Date addedOn;

    @ManyToOne
    @JoinColumn(name = "author_of_book")
    private Author author;

    public Book(Integer bookCode, String bookName, Date addedOn, Author author) {
        super();
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.addedOn = addedOn;
        this.author = author;
    }
}
