package com.autoexam.apiserver.dao;

import com.autoexam.apiserver.beans.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {
  @Query("select t from Admin t where t.name = :name")
  Optional<Admin> getOneByName(@Param("name") String name);
}
