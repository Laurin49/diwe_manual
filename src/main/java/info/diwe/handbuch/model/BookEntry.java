package info.diwe.handbuch.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BookEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String subTitle;

    @Column(columnDefinition = "TEXT")
    private String beschreibung;

    public BookEntry() {
    }

    public BookEntry(String title, String subTitle, String beschreibung) {
        this.title = title;
        this.subTitle = subTitle;
        this.beschreibung = beschreibung;
    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    // equals, hashCode, toString


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntry bookEntry = (BookEntry) o;
        return Objects.equals(id, bookEntry.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookEntry: " +
                "id=" + id +
                ", title='" + title + '\'' +
                ' ';
    }
}
