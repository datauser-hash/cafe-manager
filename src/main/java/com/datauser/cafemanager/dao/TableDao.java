package com.datauser.cafemanager.dao;

import com.datauser.cafemanager.models.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableDao extends JpaRepository<Table, String> {

    Optional<Table> findById(String id);

    Table save(Table user);

}
