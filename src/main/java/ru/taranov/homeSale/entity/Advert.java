package ru.taranov.homeSale.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="adverts")
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Заполните поле Название.")
    @Length(max = 20, message = "Название слишком большое!")
    @Column(name = "title", nullable = false)
    private String title;

    private String filename;

    @NotBlank(message = "Заполните поле Цена.")
    @Column(name = "price", nullable = false)
    private String price;

    @NotBlank(message = "Заполните поле Описание.")
    @Length(max = 2048, message = "Сообщение слишком большое!")
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    private Timestamp added;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Account author;

    public Advert() {
    }

    public Advert(String title, String price, String description, Account author) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.author = author;
    }

    public Advert(String title, String price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Timestamp getAdded() {
        return added;
    }

    public void setAdded(Timestamp added) {
        this.added = added;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advert advert = (Advert) o;
        return Objects.equals(id, advert.id) &&
                Objects.equals(title, advert.title) &&
                Objects.equals(price, advert.price) &&
                Objects.equals(description, advert.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, description);
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '}';
    }
}
