package com.example.demo;


import javax.persistence.*;
import javax.persistence.OneToMany;

@Entity
public class Admin {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String addalbum;
        @OneToMany (cascade =CascadeType.ALL)
        @JoinColumn(name ="album_id")

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddalbum() {
        return addalbum;
    }

    public void setAddalbum(String addalbum) {
        this.addalbum = addalbum;
    }
}
