package com.datauser.cafemanager.service;

import com.datauser.cafemanager.models.Table;


import java.util.List;

public interface TableService {

    Table createTable(Table table);

    List<Table> findAll();
}
