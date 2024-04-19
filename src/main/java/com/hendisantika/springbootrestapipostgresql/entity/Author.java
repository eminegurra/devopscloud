package com.hendisantika.springbootrestapipostgresql.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-rest-api-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-18
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String isbn;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "mbiemer")
    @Column(name = "Value")
    private List<String> mbiemer = new ArrayList<>();

    public Author(String name, String isbn, List<String> mbiemer) {
        this.name = name;
        this.isbn = isbn;
        this.mbiemer = mbiemer;
    }

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public List<String> getMbiemer() {
        return mbiemer;
    }

    public void setMbiemer(List<String> mbiemer) {
        this.mbiemer = mbiemer;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", mbiemer=" + mbiemer +
                '}';
    }
}
