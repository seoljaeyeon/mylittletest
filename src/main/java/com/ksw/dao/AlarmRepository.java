package com.ksw.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksw.object.entity.jpa.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long>{

}
