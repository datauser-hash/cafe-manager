package com.datauser.cafemanager.dao;

import com.datauser.cafemanager.models.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableDao extends JpaRepository<Table, Long> {

    Table findById(String id);

    Table save(Table user);

    List<Table> findAllByIdIsNotNull();
}
