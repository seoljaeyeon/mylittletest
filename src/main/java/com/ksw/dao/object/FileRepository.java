package com.ksw.dao.object;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksw.object.entity.jpa.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
