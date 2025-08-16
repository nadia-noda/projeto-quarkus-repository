package tech.ada.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Book extends PanacheEntity {

    private String isbn;
    private String tittle;
    private String author;
    private String publisher;
    @JsonProperty("publication_date")
    private LocalDate publicationDate;
    private String synopsis;
    @JsonProperty("poster_path")
    private String posterPath;
    private String genre;
    @JsonProperty("inclusion_date")
    private LocalDateTime inclusionDate;

    public Book(){};

    public Book(String isbn, String tittle, String author, String publisher, LocalDate publicationDate, String synopsis, String posterPath, LocalDateTime inclusionDate, String genre) {
        this.isbn = isbn;
        this.tittle = tittle;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.synopsis = synopsis;
        this.posterPath = posterPath;
        this.inclusionDate = LocalDateTime.now();
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public LocalDateTime getInclusionDate() {
        return inclusionDate;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
