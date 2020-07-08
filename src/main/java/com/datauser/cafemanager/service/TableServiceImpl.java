package com.datauser.cafemanager.service;

import com.datauser.cafemanager.dao.TableDao;
import com.datauser.cafemanager.models.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements TableService{

    TableDao tableDao;

    @Autowired
    public TableServiceImpl(TableDao tableDao) {
        this.tableDao = tableDao;
    }

    @Override
    public Table findById(String id) {
        return tableDao.findById(id);
    }

    @Override
    public void save(Table table) {

    }

    @Override
    public List<Table> findAll() {
        return tableDao.findAll();
    }
}
