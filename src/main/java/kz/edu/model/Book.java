package kz.edu.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "BookEntity")
@Table(name = "books")
public class Book implements Serializable
{
    private long id;
    private String name;
    private String author;
    private String genre;
    private String imageURL;
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
