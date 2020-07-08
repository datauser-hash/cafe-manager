package com.datauser.cafemanager.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User extends AbstractEntity {
    private String userRole;

    private String userName;

    private String email;

    private String password;


    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User() {
    }
}
