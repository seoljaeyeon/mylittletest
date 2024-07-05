package com.ksw.dao.forObject.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksw.object.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
