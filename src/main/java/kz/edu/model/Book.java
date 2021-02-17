package kz.edu.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "BookEntity")
@Table(name = "books")
public class Book implements Serializable
{
    private long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 30, message = "Author should be between 2 and 30 characters")
    private String author;
    @NotEmpty(message = "Genre should not be empty")
    @Size(min = 2, max = 30, message = "Genre should be between 2 and 30 characters")
    private String genre;
    @NotEmpty(message = "imageURL should not be empty")
    private String imageURL;
    @Min(value = 0, message = "Copies should be greater than or equal to 0")
    private int copies;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    public long getId()
    {
        return this.id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    @Column(name = "book_name")
    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    @Column(name = "book_author")
    public String getAuthor()
    {
        return this.author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }

    @Column(name = "book_genre")
    public String getGenre() { return this.genre; }
    public void setGenre(String genre) { this.genre = genre; }

    @Column(name = "book_poster")
    public String getImageURL() { return this.imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

    @Column(name = "book_copies")
    public int getCopies() { return this.copies; }
    public void setCopies(int copies) { this.copies = copies; }
}