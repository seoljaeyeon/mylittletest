package com.ksw.dao.forObject.object;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksw.object.entity.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long>{

}
