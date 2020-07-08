package com.datauser.cafemanager.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Table extends AbstractEntity{

    private String userId;

    private int size;

    @OneToMany(mappedBy = "table")
    private List<Order> orders;


    public Table(String userId, int size) {
        this.userId = userId;
        this.size = size;
    }

    public Table() {
    }
}
