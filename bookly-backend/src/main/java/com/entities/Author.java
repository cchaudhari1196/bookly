package com.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Author {

    @Id
    @GeneratedValue
    private int a_id;

    private String a_name;

    @ManyToMany(mappedBy = "authors", cascade = { CascadeType.ALL })
    private Set<Product> products;

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }
}
