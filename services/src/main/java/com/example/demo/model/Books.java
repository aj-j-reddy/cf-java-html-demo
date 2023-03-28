package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "\"BOOKS\"")
public class Books {
    
    @Id
    @Column(name = "\"ID\"", length = 200)
    private String id;
    @Column(name = "\"NAME\"", length = 190)
    private String name;
    @Column(name = "\"PUBLISHED_ON\"")
    private String publishedOn;
    @Column(name = "\"AUTHOR\"", length = 16)
    private String author;
    @Column(name = "\"COUNT\"", length = 250)
    private int count;
    
    public String getBookId() {
        return id;
    }
    
    public void setBookId(String id) {
        this.id = id;
    }
    
    public String getBookName() {
        return name;
    }
    
    public void setBookName(String name) {
        this.name = name;
    }
    
    public String getPublishedOn() {
        return publishedOn;
    }
    
    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
}