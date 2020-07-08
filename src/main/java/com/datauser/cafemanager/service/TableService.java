package com.datauser.cafemanager.service;

import com.datauser.cafemanager.models.Table;
import com.datauser.cafemanager.models.User;
import com.datauser.cafemanager.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface TableService {

    Table findById(String id);

    void save(Table table);

    List<Table> findAll();
}
