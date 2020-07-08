package com.datauser.cafemanager.service.impl;

import com.datauser.cafemanager.dao.TableDao;
import com.datauser.cafemanager.models.Table;
import com.datauser.cafemanager.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService {

    private TableDao tableDao;

    @Autowired
    public TableServiceImpl(TableDao tableDao) {
        this.tableDao = tableDao;
    }


    @Override
    public Table createTable(Table table) {
        return tableDao.save(table);
    }

    @Override
    public List<Table> findAll() {
        return tableDao.findAll();
    }
}
