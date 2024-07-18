package com.ksw.dao.forObject.entity;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ksw.object.entity.Note;
import com.ksw.object.entity.View;

@Repository
public interface ViewRepository extends JpaRepository<View, Integer>{

}
