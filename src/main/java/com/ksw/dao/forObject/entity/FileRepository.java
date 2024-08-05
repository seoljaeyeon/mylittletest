package com.ksw.dao.forObject.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksw.object.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
